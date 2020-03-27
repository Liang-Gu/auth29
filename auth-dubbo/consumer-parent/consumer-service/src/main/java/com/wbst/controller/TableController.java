package com.wbst.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wbst.Service.TableService;
import com.wbst.domain.AuthChange;
import com.wbst.domain.NoSwipingCard;
import com.wbst.util.AjaxResult;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/table")
public class TableController {

    @Reference
    private TableService tableService;

    /**
     * 未刷卡TOP10
     * @return
     */
    @RequestMapping(value = "/noSwipingCardTopTen",method = RequestMethod.GET)
    public AjaxResult noSwipngCardTopTen(){
        try {
            List<NoSwipingCard> noSwipingCards=tableService.noSwipngCardTopTen();
            return new AjaxResult(0,"查询成功",noSwipingCards);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1, "查询失败", null);
        }
    }


    /**
     * 周期设置
     * @param tableAnalysisCycle
     * @param tableAnalysisType
     * @return
     */
    @RequestMapping(value = "/tableCycle",method = RequestMethod.GET)
    public AjaxResult tableCycle(@RequestParam("tableAnalysisCycle")Integer tableAnalysisCycle,@RequestParam("tableAnalysisType")Integer tableAnalysisType){
        try {
            tableService.tableCycle(tableAnalysisCycle,tableAnalysisType);
            return new AjaxResult(0,"周期设置成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1,"周期设置失败",null);
        }
    }


    @RequestMapping(value = "/findCycleByTableType",method = RequestMethod.GET)
    public AjaxResult findCycleByTableType(@RequestParam("tableAnalysisType")Integer tableAnalysisType){
        try {
            Integer  tableAnalysisCycle=tableService.findCycleByTableType(tableAnalysisType);
            return new AjaxResult(0,"查询成功",tableAnalysisCycle);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(0,"网络繁忙",null);
        }
    }

    //授权变更TOP10
    @RequestMapping(value = "/authChangeTopTen",method = RequestMethod.GET)
    public AjaxResult authChangeTopTen(){
        try {
            List<AuthChange> authChangeList= tableService.authChangeTopTen();
            return new AjaxResult(0,"查询成功",authChangeList);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(0,"查询失败",null);
        }
    }

    //授权次数TOP10
    @RequestMapping(value = "/authTimes",method = RequestMethod.GET)
    public AjaxResult authTimes(){
        try {
            List<AuthChange> authChangeList= tableService.authTimes();
            return new AjaxResult(0,"查询成功",authChangeList);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(0,"查询失败",null);
        }
    }
}
