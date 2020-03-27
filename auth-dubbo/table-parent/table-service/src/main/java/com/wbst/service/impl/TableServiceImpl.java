package com.wbst.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wbst.Service.TableService;
import com.wbst.domain.AuthChange;
import com.wbst.domain.NoSwipingCard;
import com.wbst.mapper.TableMapper;

import javax.annotation.Resource;
import java.util.List;

@Service(timeout = 3000)
public class TableServiceImpl implements TableService {

    @Resource
    private TableMapper tableMapper;

    /**
     * 查询长未刷卡top10
     * @return
     */
    @Override
    public List<NoSwipingCard> noSwipngCardTopTen() {
       List<NoSwipingCard> list= tableMapper.noSwipngCardTopTen();
        return list;
    }

    /**
     * 周期设置
     * @param tableAnalysisCycle
     * @param tableAnalysisType
     */
    @Override
    public void tableCycle(Integer tableAnalysisCycle, Integer tableAnalysisType) {
        tableMapper.tableCycle(tableAnalysisCycle,tableAnalysisType);
    }


    /**
     * 查询授权变更次数TOP10
     * @return
     */
    @Override
    public List<AuthChange> authChangeTopTen() {
       return tableMapper.authChangeTopTen();
    }


    /**
     * //授权次数TOP10
     * @return
     */
    @Override
    public List<AuthChange> authTimes() {
        return tableMapper.authTimes();
    }

    @Override
    public Integer findCycleByTableType(Integer tableAnalysisType) {

      return   tableMapper.findCycleByTableType(tableAnalysisType);
    }
}
