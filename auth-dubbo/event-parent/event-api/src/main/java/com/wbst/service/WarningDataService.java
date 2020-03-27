package com.wbst.service;

import com.wbst.domain.WarningData;
import com.wbst.domain.WarningDataDetail;
import com.wbst.dto.WarningDataDto;
import com.wbst.query.WarningDataQuery;
import com.wbst.util.PageList;

import java.util.List;

public interface WarningDataService {



    //保存异常数据到异常表中
    void save(List<WarningDataDto> personList);

    //异常处理
    void handle(WarningData warningData);

    //查询所有未处理异常数据
    PageList<WarningData> findAllAbnormal(WarningDataQuery warningDataQuery);

    //查询处理详情
    WarningData findDetailByPid(Long pid);

    //处理记录查询
    PageList<WarningData> findByWarningDataQuery(WarningDataQuery warningDataQuery);
}
