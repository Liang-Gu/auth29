package com.wbst.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wbst.domain.Visitor;
import com.wbst.query.VisitorQuery;
import com.wbst.service.VisitorService;
import com.wbst.util.AjaxResult;
import com.wbst.util.PageList;
import org.springframework.web.bind.annotation.*;

/**
 * 访客管理
 */
@RestController
@RequestMapping("/visitor")
public class VisitorController {

    @Reference
    private VisitorService visitorService;


    /**
     * 访客管理
     * @param visitorQuery
     * @return
     */
    @RequestMapping(value = "findAllByQuery",method = RequestMethod.POST)
    public AjaxResult findAll(@RequestBody VisitorQuery visitorQuery){
        try {
            PageList<Visitor> visitorList = visitorService.findAllByQuery(visitorQuery);
            return new AjaxResult(0, "查询成功",visitorList );
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1, "查询失败,原因为"+e.getMessage(),null );
        }
    }

    /**
     * xxx被访记录查询
     */
    @RequestMapping(value = "/findByVisitedName",method = RequestMethod.GET)
    public AjaxResult findByVisitedName(@RequestBody VisitorQuery visitorQuery){
        try {
            PageList<Visitor> visitorPageList= visitorService.findByVisitedName(visitorQuery);
            return new AjaxResult(0, "查询成功", visitorPageList);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1, "查询失败", null);
        }
    }
}
