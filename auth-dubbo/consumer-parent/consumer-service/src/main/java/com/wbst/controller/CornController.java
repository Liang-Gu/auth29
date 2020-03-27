package com.wbst.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wbst.domain.Corn;
import com.wbst.service.CornService;
import com.wbst.util.AjaxResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/corn")
public class CornController {

    @Reference
    private CornService cornService;

    //修改corn异常间隔日期
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public AjaxResult update(@RequestParam("abnormalDuration")Integer abnormalDuration){
        try {
            cornService.update(abnormalDuration);
            return new AjaxResult(0,"修改成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1,"修改失败",null);
        }
    }


    //查询间隔日期
    @RequestMapping(value = "/find",method = RequestMethod.GET)
    public AjaxResult find(){
        try {
            Corn corn= cornService.find();
            return new AjaxResult(0,"查询成功",corn);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1,"查询失败",null);
        }
    }
}
