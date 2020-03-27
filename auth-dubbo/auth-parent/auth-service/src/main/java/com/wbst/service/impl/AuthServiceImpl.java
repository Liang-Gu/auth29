package com.wbst.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wbst.constant.LenelAuthConstant;
import com.wbst.domain.*;
import com.wbst.mapper.AuthMapper;
import com.wbst.query.AccessLevelQuery;
import com.wbst.query.AuthLogQuery;
import com.wbst.query.AuthQuery;
import com.wbst.service.AuthLogService;
import com.wbst.service.AuthService;
import com.wbst.service.PersonService;
import com.wbst.util.AuthUtil;
import com.wbst.util.LenelAuthUtil;
import com.wbst.util.PageList;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.websocket.Session;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service(timeout = 30000)
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthMapper authMapper;

    @Reference
    private PersonService personService;

    @Autowired
    private AuthLogService authLogService;

    //添加权限
    @Override
    @Transactional
    public void add(AuthQuery authQuery) {
        //获取员工芯片卡号集合
        List<String> cardNums = authQuery.getCardNums();

        //获取通行权限的编号集合
        List<AuthUtil> authUtils = authQuery.getAuths();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //循环遍历所有授权编号
        for (String cardNum : cardNums) {
            //查询员工信息
            Person userDto = personService.findByCardNum(cardNum);
            //循环遍历所有通行权限集合
            for (AuthUtil authUtil : authUtils) {
                //根据通行权限和授权编号查询数据
                Auth auth = authMapper.findByCardNumAndaccessLevelId(cardNum, authUtil.getAccessLevelId());
                //判断对象是否有数据
                if(auth!=null){//数据库有数据
                    //操作日志 操作内容
                    String operationContent=null;
                    //修改数据
                    //判断通行权限是否为永久全权限
                    if(authUtil.getAccessLevelStatus()==0){//永久权限
                        //修改数数据
                        authMapper.update(cardNum,authUtil.getAccessLevelId(),null,null,0);
                        operationContent="修改"+authUtil.getAccessLevelName()+"通行权限为永久权限";
                        AuthLog authLog = new AuthLog();
                        authLog.setOperationTime(new Date()).setOperationName("测试+授权处").setActionObject(userDto.getPersonName()).setOperationContent(operationContent).setOperationDesc("添加时权限修改");
                        authLogService.saveOperation(authLog);
                        //删除lenel数据
//                   deleteLenel(cardNum.toString(),auth.getAccessLevelId().toString(),auth.getEffectiveTime_start(),auth.getEffectiveTime_end());
//                   lenelAuth(cardNum.toString(),auth.getAccessLevelId().toString(),auth.getEffectiveTime_start(),auth.getEffectiveTime_end());
                    }else {
                        if(auth.getAccessLevelStatus()==0 ||auth.getEffectiveTimeEnd().after(authUtil.getEffectiveTimeEnd())){
                                continue;
                        }else {
                            authMapper.update(cardNum,authUtil.getAccessLevelId(),authUtil.getEffectiveTimeStart(),authUtil.getEffectiveTimeEnd(),1);
                            operationContent="修改"+authUtil.getAccessLevelName()+"通行权限时间为："+df.format(authUtil.getEffectiveTimeStart())+"至"+df.format(authUtil.getEffectiveTimeEnd());
                            AuthLog authLog = new AuthLog();
                            authLog.setOperationTime(new Date()).setOperationName("测试+授权处").setActionObject(userDto.getPersonName()).setOperationContent(operationContent).setOperationDesc("添加时权限修改");
                            authLogService.saveOperation(authLog);
                            //删除lenel数据
//                   deleteLenel(cardNum.toString(),auth.getAccessLevelId().toString(),auth.getEffectiveTime_start(),auth.getEffectiveTime_end());
//                   lenelAuth(cardNum.toString(),auth.getAccessLevelId().toString(),auth.getEffectiveTime_start(),auth.getEffectiveTime_end());
                        }
                    }
                }else {//数据库没数据
                    authMapper.insert(cardNum,authUtil.getAccessLevelId(),authUtil.getEffectiveTimeStart(),authUtil.getEffectiveTimeEnd(),authUtil.getAccessLevelStatus());
                    String operationContent=null;
                    if(authUtil.getAccessLevelStatus()==1){//临时卡
                        operationContent="新增"+authUtil.getAccessLevelName()+"通行权限，有效时间为："+df.format(authUtil.getEffectiveTimeStart())+"至"+df.format(authUtil.getEffectiveTimeEnd());
                    }else {
                        operationContent="新增"+authUtil.getAccessLevelName()+"通行权限，有效时间为：永久";
                    }
                    AuthLog authLog = new AuthLog();
                    authLog.setOperationTime(new Date()).setOperationName("测试+授权处").setActionObject(userDto.getPersonName()).setOperationContent(operationContent).setOperationDesc("权限新增");
                    //保存操作日志
                    authLogService.saveOperation(authLog);

                    personService.updateByPerAuthSn(cardNum,df.format(new Date()));
//                   lenelAuth(cardNum.toString(),auth.getAccessLevelId().toString(),auth.getEffectiveTime_start(),auth.getEffectiveTime_end());
                }
            }
        }
    }

    /**
     * 查询所有通行权限
     * @return
     */
    @Override
    public PageList<AccessLevel> findAllAccessLevel(AccessLevelQuery accessLevelQuery) {
       Page<AccessLevel> page= PageHelper.startPage(accessLevelQuery.getCurrentPage(),accessLevelQuery.getPageSize());
       List<AccessLevel> accessLevelList= authMapper.findAllAccessLevel(accessLevelQuery);
        return new PageList<>(page.getTotal(),page.getPages(),accessLevelList);
    }

    /**
     * 根据授权编号查询共有通行权限
     * @param authQuery
     * @return
     */
    @Override
    public List<AccessLevel> findCommon(AuthQuery authQuery) {
        int size = authQuery.getCardNums().size();
        //查询在授权表中查询共有权限
       List<AccessLevel> accessLevelIds=authMapper.findCommon(authQuery.getCardNums(),size);
        return accessLevelIds;
    }


    /**
     * 修改
     * @param authQuery
     */
    @Override
    @Transactional
    public void batchUpdate(AuthQuery authQuery) {
        //获取员工芯片卡号集合
        List<String> cardNums = authQuery.getCardNums();

        //获取通行权限的编号集合
        List<AuthUtil> authUtils = authQuery.getAuths();

        //判断修改权限是否为永久权限
        for (AuthUtil authUtil : authUtils) {
            if(authUtil.getAccessLevelStatus()==0){//修改为永久权限
                for (String cardNum : cardNums) {
                    authMapper.updateForever(cardNum,authUtil.getAccessLevelId());
                    Person byCardNum = personService.findByCardNum(cardNum);
                    //删除lenel数据
//                   deleteLenel(cardNum.toString(),auth.getAccessLevelId().toString(),auth.getEffectiveTime_start(),auth.getEffectiveTime_end());
//                   lenelAuth(cardNum.toString(),auth.getAccessLevelId().toString(),auth.getEffectiveTime_start(),auth.getEffectiveTime_end());
                    AuthLog authLog = new AuthLog();
                    String operationContent="修改通行权限"+authUtil.getAccessLevelName()+"为永久权限";
                    authLog.setOperationTime(new Date()).setOperationName("测试+修改处").setActionObject(byCardNum.getPersonName()).setOperationContent(operationContent).setOperationDesc("修改权限");
                    authLogService.saveOperation(authLog);
                }
            }else {
                Auth auth = authMapper.findByCardNumAndaccessLevelId(cardNums.get(0), authUtil.getAccessLevelId());
                if(auth.getAccessLevelStatus()==0 ||auth.getEffectiveTimeEnd().after(authUtil.getEffectiveTimeEnd())){
                    continue;
                }else {
                    authMapper.batchUpdate(cardNums,authUtil.getAccessLevelId(),authUtil.getEffectiveTimeStart(),authUtil.getEffectiveTimeEnd(),1);
                    String operationContent="修改"+authUtil.getAccessLevelName()+"通行权限时间为："+authUtil.getEffectiveTimeStart()+"至"+authUtil.getEffectiveTimeEnd();
                    for (String cardNum : cardNums) {
                        Person byCardNum = personService.findByCardNum(cardNum);
                        AuthLog authLog = new AuthLog();
                        authLog.setOperationTime(new Date()).setOperationName("测试+修改处").setActionObject(byCardNum.getPersonName()).setOperationContent(operationContent).setOperationDesc("修改权限");
                        authLogService.saveOperation(authLog);
                        //删除lenel数据
//                   deleteLenel(cardNum.toString(),auth.getAccessLevelId().toString(),auth.getEffectiveTime_start(),auth.getEffectiveTime_end());
//                   lenelAuth(cardNum.toString(),auth.getAccessLevelId().toString(),auth.getEffectiveTime_start(),auth.getEffectiveTime_end());
                    }
            }
        }

    }
    }


    /**
     * 删除
     * @param authQuery
     */
    @Override
    @Transactional
    public void batchDelete(AuthQuery authQuery) {
        //获取员工芯片卡号集合
        List<String> cardNums = authQuery.getCardNums();

        //获取通行权限的编号集合
        List<AuthUtil> authUtils = authQuery.getAuths();

        for (String cardNum : cardNums) {
            Person byCardNum = personService.findByCardNum(cardNum);
            authMapper.batchDelete(cardNum,authUtils);
            for (AuthUtil authUtil : authUtils) {


                AuthLog authLog = new AuthLog();
                String operationContent="删除权限:"+authUtils.stream().map(authUtil1 -> authUtil1.getAccessLevelName()).collect(Collectors.toList());
                authLog.setOperationTime(new Date()).setOperationName("测试+删除").setActionObject(byCardNum.getPersonName()).setOperationContent(operationContent).setOperationDesc("删除权限");
                authLogService.saveOperation(authLog);

                //删除lenel数据
//          deleteLenel(cardNum.toString(),auth.getAccessLevelId().toString(),auth.getEffectiveTime_start(),auth.getEffectiveTime_end());
            }
            }


    }

    /**
     * 查询授权操作日志
     * @param authlogQuery
     * @return
     */
    @Override
    public PageList<AuthLog> findAllAuthLog(AuthLogQuery authlogQuery) {
        return authLogService.findAllAuthLog(authlogQuery);
    }


    /**
     * 向lenel新增通行权限
     */
    private void lenelAuth(String badgeID, String accessLevelID, Date startTime,Date endTime){
        //获得HTTPClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //参数
        StringBuffer params=new StringBuffer();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddd :HH:mm:ss");


        //创建POST请求
        HttpPost httpPost = new HttpPost(LenelAuthConstant.LENELADDRESS+"/api/LenelDataUpdate/Authorize");
        //准备参数
        LenelAuthUtil lenelAuthUtil = new LenelAuthUtil();
        lenelAuthUtil.setBadgeID(badgeID);
        lenelAuthUtil.setAccessLevelID(accessLevelID);
        lenelAuthUtil.setStartTime(simpleDateFormat.format(startTime));
        lenelAuthUtil.setEndTime(simpleDateFormat.format(endTime));
        lenelAuthUtil.setActionID(1);
        //将对象转为JSON字符串
        String jsonString = JSON.toJSONString(lenelAuthUtil);

        StringEntity stringEntity = new StringEntity(jsonString, "UTF-8");

        //发送请求
        httpPost.setEntity(stringEntity);

        //设置请求头
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        //响应模型
        CloseableHttpResponse response = null;

        try {
            //由客户端执行发送Post请求
            response = httpClient.execute(httpPost);

            //从响应模型中取出响应实体
            HttpEntity responseEntity = response.getEntity();

            //打印返回信息
            System.out.println(EntityUtils.toString(responseEntity));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    /**
     * 删除lenel中的通行权限
     */
    private void deleteLenel(String badgeID, String accessLevelID, Date startTime,Date endTime){
        //获得HTTPClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //参数
        StringBuffer params=new StringBuffer();

        //创建POST请求
        HttpPost httpPost = new HttpPost(LenelAuthConstant.LENELADDRESS+"/api/LenelDataUpdate/Authorize");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddd :HH:mm:ss");
        //准备参数
        LenelAuthUtil lenelAuthUtil = new LenelAuthUtil();
        lenelAuthUtil.setBadgeID(badgeID);
        lenelAuthUtil.setAccessLevelID(accessLevelID);
        lenelAuthUtil.setStartTime(simpleDateFormat.format(startTime));
        lenelAuthUtil.setEndTime(simpleDateFormat.format(endTime));
        lenelAuthUtil.setActionID(2);
        //将对象转为JSON字符串
        String jsonString = JSON.toJSONString(lenelAuthUtil);

        StringEntity stringEntity = new StringEntity(jsonString, "UTF-8");

        //发送请求
        httpPost.setEntity(stringEntity);

        //设置请求头
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        //响应模型
        CloseableHttpResponse response = null;

        try {
            //由客户端执行发送Post请求
            response = httpClient.execute(httpPost);

            //从响应模型中取出响应实体
            HttpEntity responseEntity = response.getEntity();

            //打印返回信息
            System.out.println(EntityUtils.toString(responseEntity));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}

