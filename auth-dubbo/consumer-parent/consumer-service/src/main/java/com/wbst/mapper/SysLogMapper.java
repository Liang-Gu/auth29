package com.wbst.mapper;

import com.wbst.domain.SysLog;
import org.springframework.stereotype.Repository;

@Repository
public interface SysLogMapper {

    void save(SysLog sysLog);
}
