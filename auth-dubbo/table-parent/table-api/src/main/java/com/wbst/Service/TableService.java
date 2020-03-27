package com.wbst.Service;

import com.wbst.domain.AuthChange;
import com.wbst.domain.NoSwipingCard;

import java.util.List;

public interface TableService {


    //查询长未刷卡top10
    List<NoSwipingCard> noSwipngCardTopTen();

    //周期设置
    void tableCycle(Integer tableAnalysisCycle, Integer tableAnalysisType);

    //查询授权变更次数TOP10
    List<AuthChange> authChangeTopTen();

    //授权次数TOP10
    List<AuthChange> authTimes();

    //查询周期
    Integer findCycleByTableType(Integer tableAnalysisType);
}
