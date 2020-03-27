package com.wbst.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wbst.domain.VisitorDetail;
import com.wbst.mapper.visitorMapper.VisitorDetailMapper;
import com.wbst.query.VisitorDetailQuery;
import com.wbst.service.VisitorDetailService;
import com.wbst.util.PageList;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VisitorDetailServiceImpl implements VisitorDetailService {

    @Resource
    private VisitorDetailMapper visitorDetailMapper;

    /**
     * 通行记录查询/xx访问记录
     * @param visitorDetailQuery
     * @return
     */
    @Override
    public PageList<VisitorDetail> findByQuery(VisitorDetailQuery visitorDetailQuery) {
        Page<VisitorDetail> page = PageHelper.startPage(visitorDetailQuery.getCurrentPage(), visitorDetailQuery.getPageSize());
        List<VisitorDetail> list=visitorDetailMapper.findByQuery(visitorDetailQuery);
        return new PageList<>(page.getTotal(),page.getPages(),list );
    }

}
