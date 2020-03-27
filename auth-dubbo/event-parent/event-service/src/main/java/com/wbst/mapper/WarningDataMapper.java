package com.wbst.mapper;

import com.wbst.domain.WarningData;
import com.wbst.domain.WarningDataDetail;
import com.wbst.dto.WarningDataDto;
import com.wbst.query.WarningDataQuery;

import java.util.List;

public interface WarningDataMapper {


    //根据人员编号查询异常数据表中是否有数据
    WarningData findByPersonSn(String personSn);

    //保存异常数据到异常表中
    void save(WarningDataDto warningDataDto);

    //修改异常表中异常数
    void update(WarningDataDto warningDataDto);

    //异常处理
    void handle(WarningData warningData);


    //查询所有未处理异常数据
    List<WarningData> findAllAbnormal(WarningDataQuery warningDataQuery);

    //保存处理详情
    void saveDetail(WarningDataDetail warningDataDetail);

    //将被处理的数据从异常表中删除
    void deleteAbnormalFromWarningData(String personSn);

    //查询处理记录
    List<WarningData> findByWarningDataQuery(WarningDataQuery warningDataQuery);

    //修改已处理异常信息
    void updateHandleData(WarningData warningData);

    //查询处理详情
    WarningData findDetailByPid(Long pid);

}
