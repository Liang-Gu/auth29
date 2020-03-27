package com.wbst.mapper.visitorMapper;

import com.wbst.domain.VisitorDetail;
import com.wbst.query.VisitorDetailQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public interface VisitorDetailMapper {

    //通行记录/xx访问记录
    List<VisitorDetail> findByQuery(VisitorDetailQuery visitorDetailQuery);
}
