package com.wbst.service;

import com.wbst.domain.AuthLog;
import com.wbst.query.AuthLogQuery;
import com.wbst.util.PageList;

import java.util.List;

public interface AuthLogService {

    //保存操作日志
    void saveOperation(AuthLog authLog);

    //查询所有操作日志
    PageList<AuthLog> findAllAuthLog(AuthLogQuery authlogQuery);



}
