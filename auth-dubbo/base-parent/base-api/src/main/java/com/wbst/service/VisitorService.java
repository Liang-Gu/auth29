package com.wbst.service;

import com.wbst.domain.Visitor;
import com.wbst.query.VisitorQuery;
import com.wbst.util.PageList;


public interface VisitorService {
    //新增访客信息
    void  save(Visitor visitor);

    //修改访客信息
    void update(Visitor visitor);

    //查询访客信息
    PageList<Visitor> findAllByQuery(VisitorQuery visitorQuery);

    //xxx被访记录查询
    PageList<Visitor> findByVisitedName(VisitorQuery visitorQuery);
}
