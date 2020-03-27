package com.wbst.mapper;

import com.wbst.domain.Corn;
import org.apache.ibatis.annotations.Param;

public interface CornMapper {
    Corn findCorn();

    void update(@Param("cron") String cron, @Param("abnormalDuration") Integer abnormalDuration);
}
