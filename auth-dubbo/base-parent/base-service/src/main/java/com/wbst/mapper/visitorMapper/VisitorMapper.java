package com.wbst.mapper.visitorMapper;

import com.wbst.domain.Visitor;
import com.wbst.query.VisitorQuery;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
public interface VisitorMapper {
    //保存
    public void  save(Visitor visitor);

    //修改
    public  void update(Visitor visitor);

    //查询所有
    public List<Visitor> findAllByQuery(VisitorQuery visitorQuery);

    //xxx被访记录查询
    List<Visitor> findByVisitedName(String visitedName);
}
