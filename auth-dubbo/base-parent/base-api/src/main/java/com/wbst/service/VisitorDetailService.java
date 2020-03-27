package com.wbst.service;

import com.wbst.domain.VisitorDetail;
import com.wbst.query.VisitorDetailQuery;
import com.wbst.util.PageList;


public interface VisitorDetailService {

    PageList<VisitorDetail> findByQuery(VisitorDetailQuery visitorDetailQuery);
}
