package com.wbst.mapper;

import com.wbst.domain.AuthLog;
import com.wbst.query.AuthLogQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthLogMapper {

    //保存操作日志
    void saveOperation(AuthLog authLog);

    //查询所有操作日志
    List<AuthLog> findAllAuthLog(AuthLogQuery authlogQuery);
}
