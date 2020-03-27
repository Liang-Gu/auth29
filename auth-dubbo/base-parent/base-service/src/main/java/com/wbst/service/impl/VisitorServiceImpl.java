package com.wbst.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wbst.domain.Visitor;
import com.wbst.mapper.visitorMapper.VisitorMapper;
import com.wbst.query.VisitorQuery;
import com.wbst.service.VisitorService;
import com.wbst.util.PageList;

import javax.annotation.Resource;
import java.util.List;

@Service(timeout = 30000)
public class VisitorServiceImpl implements VisitorService {

    @Resource
    private VisitorMapper visitorMapper;

    /**
     * 保存访客信息
     * @param visitor
     */
    @Override
    public void save(Visitor visitor) {
        visitorMapper.save(visitor);
    }

    /**
     * 修改访客信息
     * @param visitor
     * @return
     */
    @Override
    public void update(Visitor visitor) {
        visitorMapper.update(visitor);
    }

    /**
     * 根据条件查询所有访客信息
     * @param visitorQuery
     * @return
     */
    @Override
    public PageList<Visitor> findAllByQuery(VisitorQuery visitorQuery) {
        Page<Visitor> page = PageHelper.startPage(visitorQuery.getCurrentPage(), visitorQuery.getPageSize());
        List<Visitor> visitorList = visitorMapper.findAllByQuery(visitorQuery);
        return new PageList<Visitor>(page.getTotal(),page.getPages(),visitorList);
    }

    /**
     * xxx被访记录查询
     * @param visitorQuery
     * @return
     */
    @Override
    public PageList<Visitor> findByVisitedName(VisitorQuery visitorQuery) {
        Page<Visitor> page = PageHelper.startPage(visitorQuery.getCurrentPage(), visitorQuery.getPageSize());
        List<Visitor> visitorList=  visitorMapper.findByVisitedName(visitorQuery.getVisitedName());
        return new PageList<>(page.getTotal(),page.getPages(),visitorList );
    }
}
