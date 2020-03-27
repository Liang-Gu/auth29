package com.wbst.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wbst.domain.AuthLog;
import com.wbst.mapper.AuthLogMapper;
import com.wbst.mapper.WhiteAuthLogMapper;
import com.wbst.query.AuthLogQuery;
import com.wbst.service.AuthLogService;
import com.wbst.service.WhiteAuthLogService;
import com.wbst.util.PageList;

import javax.annotation.Resource;
import java.util.List;

@Service(timeout =3000 )
@org.springframework.stereotype.Service
public class WhiteAuthLogServiceImpl implements WhiteAuthLogService {

    @Resource
    private WhiteAuthLogMapper whiteAuthLogMapper;
    /**
     * 保存操作日志
     * @param authLog
     */
    @Override
    public void saveOperation(AuthLog authLog) {
        whiteAuthLogMapper.saveOperation(authLog);
    }

    /**
     * 插叙所有操作日志
     * @param authlogQuery
     * @return
     */
    @Override
    public PageList<AuthLog> findAllAuthLog(AuthLogQuery authlogQuery) {
       Page<AuthLog> page= PageHelper.startPage(authlogQuery.getCurrentPage(),authlogQuery.getPageSize());
       List<AuthLog> logs=whiteAuthLogMapper.findAllAuthLog(authlogQuery);
        return new PageList<>(page.getTotal(),page.getPages(),logs);
    }


}
