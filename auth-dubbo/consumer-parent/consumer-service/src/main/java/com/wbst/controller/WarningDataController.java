package com.wbst.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wbst.domain.WarningData;
import com.wbst.query.WarningDataQuery;
import com.wbst.service.WarningDataService;
import com.wbst.util.AjaxResult;
import com.wbst.util.PageList;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/warningData")
public class WarningDataController {

    @Reference
    private WarningDataService warningDataService;



    /**
     * 异常处理
     * @param warningData
     * @return
     */
    @RequestMapping(value = "/handle",method = RequestMethod.POST)
    public AjaxResult handle(@RequestBody WarningData warningData){
        try {
            warningDataService.handle(warningData);
            return new AjaxResult(0,"操作成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1,"操作失败，原因为"+e.getMessage(),null);
        }
    }

    //异常查询
    @RequestMapping(value = "/findAllAbnormal",method = RequestMethod.POST)
    private AjaxResult findAllAbnormal(@RequestBody WarningDataQuery warningDataQuery){
        try {
            PageList<WarningData> pageList= warningDataService.findAllAbnormal(warningDataQuery);
            return new AjaxResult(0,"查询成功",pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1,"查询失败，原因为"+e.getMessage(),null);
        }
    }

    //处理记录查询
    @RequestMapping(value = "/findByWarningDataQuery",method = RequestMethod.POST)
    public  AjaxResult findByWarningDataQuery(@RequestBody WarningDataQuery warningDataQuery){
        try {
            PageList<WarningData> warningDataDetailList= warningDataService.findByWarningDataQuery(warningDataQuery);
            return new AjaxResult(0,"查询成功",warningDataDetailList);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(0,"查询成功，原因为"+e.getMessage(),null);
        }
    }

    //处理记录详情查询
    @RequestMapping(value = "/findDetailByPid",method = RequestMethod.GET)
    public AjaxResult findDetailByPid(@RequestParam("pid")Long pid){
        try {
            WarningData warningData=warningDataService.findDetailByPid(pid);
            return new AjaxResult(0,"查询成功",warningData);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(0,"查询失败，原因为"+e.getMessage(),null);
        }
    }
}
