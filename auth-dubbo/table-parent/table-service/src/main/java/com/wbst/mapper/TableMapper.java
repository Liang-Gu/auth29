package com.wbst.mapper;

import com.wbst.domain.AuthChange;
import com.wbst.domain.NoSwipingCard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TableMapper {

    //查询长未刷卡top10
    List<NoSwipingCard> noSwipngCardTopTen();

    void tableCycle(@Param("tableAnalysisCycle") Integer tableAnalysisCycle, @Param("tableAnalysisType") Integer tableAnalysisType);

    //查询授权变更次数TOP10
    List<AuthChange> authChangeTopTen();

    //授权次数TOP10
    List<AuthChange> authTimes();

    Integer findCycleByTableType(Integer tableAnalysisType);
}
