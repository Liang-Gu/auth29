package com.wbst.service.impl;

import com.wbst.domain.SysLog;
import com.wbst.mapper.SysLogMapper;
import com.wbst.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public void save(SysLog sysLog) {
        sysLogMapper.save(sysLog);
    }
}
