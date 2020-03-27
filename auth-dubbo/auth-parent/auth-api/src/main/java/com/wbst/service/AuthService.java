package com.wbst.service;


import com.wbst.domain.AccessLevel;
import com.wbst.domain.AuthLog;
import com.wbst.query.AccessLevelQuery;
import com.wbst.query.AuthLogQuery;
import com.wbst.query.AuthQuery;
import com.wbst.util.PageList;

import java.util.List;

public interface AuthService {
    //批量添加权限
    void add(AuthQuery authQuery);

    //查询所有的通行权限
    PageList<AccessLevel> findAllAccessLevel(AccessLevelQuery accessLevelQuery);

    //根据授权编号查询共有通行权限
    List<AccessLevel> findCommon(AuthQuery authQuery);

    //批量修改
    void batchUpdate(AuthQuery authQuery);

    //批量删除
    void batchDelete(AuthQuery authQuery);

    PageList<AuthLog> findAllAuthLog(AuthLogQuery authlogQuery);
}
