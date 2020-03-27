package com.wbst.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wbst.domain.WarningData;
import com.wbst.domain.WarningDataDetail;
import com.wbst.dto.WarningDataDto;
import com.wbst.mapper.WarningDataMapper;
import com.wbst.query.WarningDataQuery;
import com.wbst.service.WarningDataService;
import com.wbst.util.PageList;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service(timeout = 3000)
@org.springframework.stereotype.Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class WarningDataServiceImpl implements WarningDataService {

    @Resource
    private WarningDataMapper warningDataMapper;

    /**
     * 保存异常数据到异常信息表中
     * @param personList
     */
    @Override
    @Transactional
    public void save(List<WarningDataDto> personList) {
        //循环遍历异常数据
        for (WarningDataDto warningDataDto : personList) {
            //根据人员编号查询异常数据表中是否有数据
           WarningData warningData= warningDataMapper.findByPersonSn(warningDataDto.getPersonSn());
           if(warningData==null){
               warningDataMapper.save(warningDataDto);
           }else {
               warningDataMapper.update(warningDataDto);
           }
        }

    }

    /**
     * 异常处理
     * @param warningData
     */
    @Override
    @Transactional
    public void handle(WarningData warningData) {
        WarningData warningData1= warningDataMapper.findByPersonSn(warningData.getPersonSn());
       if(warningData1==null){
           //处理异常 将处理后的异常数据保存到异常处理数据表中
           warningDataMapper.handle(warningData);
           //保存处理异常信息到处理异常信息详情表中
           List<WarningDataDetail> warningDataDetails = warningData.getWarningDataDetails();
           WarningDataDetail warningDataDetail=warningDataDetails.get(0);
           warningDataDetail.setPid(warningData.getWarningId());
           warningDataMapper.saveDetail(warningDataDetail);
           //将被处理的数据从异常表中删除
           warningDataMapper.deleteAbnormalFromWarningData(warningData.getPersonSn());
       }else {
           //修改
           warningDataMapper.updateHandleData(warningData);
           //保存处理异常信息到处理异常信息详情表中
           List<WarningDataDetail> warningDataDetails = warningData.getWarningDataDetails();
           WarningDataDetail warningDataDetail=warningDataDetails.get(0);
           warningDataDetail.setPid(warningData.getWarningId());
           warningDataMapper.saveDetail(warningDataDetail);
           //将被处理的数据从异常表中删除
           warningDataMapper.deleteAbnormalFromWarningData(warningData.getPersonSn());
       }


    }

    /**
     * 查询未处理异常数据
     * @param warningDataQuery
     * @return
     */
    @Override
    public PageList<WarningData> findAllAbnormal(WarningDataQuery warningDataQuery) {
        Page<WarningData> page = PageHelper.startPage(warningDataQuery.getCurrentPage(), warningDataQuery.getPageSize());
        List<WarningData> list= warningDataMapper.findAllAbnormal(warningDataQuery);
        return new PageList<WarningData>(page.getTotal(),page.getPages(),list);
    }

    @Override
    public WarningData findDetailByPid(Long pid) {
        return warningDataMapper.findDetailByPid(pid);
    }

    /**
     * 查询处理记录
     * @param warningDataQuery
     * @return
     */
    @Override
    public PageList<WarningData> findByWarningDataQuery(WarningDataQuery warningDataQuery) {
        Page<WarningData> page = PageHelper.startPage(warningDataQuery.getCurrentPage(), warningDataQuery.getPageSize());
        List<WarningData> list= warningDataMapper.findByWarningDataQuery(warningDataQuery);
        return new PageList<WarningData>(page.getTotal(),page.getPages(),list);
    }
}
