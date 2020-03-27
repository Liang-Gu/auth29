package com.wbst.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wbst.annotation.MyLog;
import com.wbst.domain.AccessLevel;
import com.wbst.domain.AuthLog;
import com.wbst.query.AccessLevelQuery;
import com.wbst.query.AuthLogQuery;
import com.wbst.query.AuthQuery;
import com.wbst.service.AuthService;
import com.wbst.service.WhiteAuthService;
import com.wbst.util.AjaxResult;
import com.wbst.util.PageList;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/whiteAuth")
public class WhiteAuthController {


    @Reference
    private WhiteAuthService whiteAuthService;

    @RequestMapping("/add")
    @MyLog("批量添加权限")
    public AjaxResult add(@RequestBody AuthQuery authQuery){
        try {
            whiteAuthService.add(authQuery);
            return new AjaxResult(0,"添加权限成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            return  new AjaxResult(1,"权限添加失败，原因为"+e.getMessage(),null);
        }
    }


    /**
     * 查询所有的通行权限
     * @param accessLevelQuery
     * @return
     */
    @RequestMapping("/findAllAccessLevel")
    public AjaxResult findAllAccessLevel(@RequestBody AccessLevelQuery accessLevelQuery){
        try {
            PageList<AccessLevel> accessLevelPageList= whiteAuthService.findAllAccessLevel(accessLevelQuery);
            return new AjaxResult(0,"查询成功",accessLevelPageList);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1,"查询失败",null);
        }
    }


    /**
     * 批量修改：
     *          根据不同员工授权编号查询共有通行权限(通行权限编号与通行时间一致)
     * @return
     */
    @RequestMapping("/findCommon")
    public AjaxResult findCommon(@RequestBody AuthQuery authQuery){
        try {
            List<AccessLevel> accessLevelList= whiteAuthService.findCommon(authQuery);
            return new AjaxResult(0,"查询成功", accessLevelList);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1,"查询失败",null);
        }
    }


    /**
     * 批量修改
     * @param authQuery
     * @return
     */
    @RequestMapping(value = "/batchUpdate",method = RequestMethod.POST)
    public AjaxResult batchUpdate(@RequestBody AuthQuery authQuery){
        try {
            whiteAuthService.batchUpdate(authQuery);
            return new AjaxResult(0,"修改成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1,"修改失败",null);
        }
    }

    /**
     * 批量删除
     * @param authQuery
     * @return
     */
    @RequestMapping(value = "/batchDelete",method = RequestMethod.POST)
    public AjaxResult batchDelete(@RequestBody AuthQuery authQuery){
        try {
            whiteAuthService.batchDelete(authQuery);
            return new AjaxResult(0,"删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(0,"删除失败", null);

        }
    }


    @RequestMapping("/findAllAuthLog")
    public AjaxResult findAllAuthLog(@RequestBody AuthLogQuery authlogQuery){
        try {
            PageList<AuthLog> authLogQueryPageList= whiteAuthService.findAllAuthLog(authlogQuery);
            return new AjaxResult(0,"授权操作日志查询成功", authLogQueryPageList);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(0,"授权操作日志查询失败", null);
        }
    }
}
