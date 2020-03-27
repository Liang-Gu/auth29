package com.wbst.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wbst.domain.VisitorDetail;
import com.wbst.query.VisitorDetailQuery;
import com.wbst.service.VisitorDetailService;
import com.wbst.util.AjaxResult;
import com.wbst.util.PageList;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/visitorDetail")
public class VisitorDetailController {

    @Reference
    private VisitorDetailService visitorDetailService;

    /**
     * 通行记录/xx访问记录
     * @param visitorDetailQuery
     * @return
     */
    @RequestMapping(value = "/findByQuery",method = RequestMethod.POST)
    public  AjaxResult findByQuery(@RequestBody VisitorDetailQuery visitorDetailQuery){

        try {
            PageList<VisitorDetail> pageList= visitorDetailService.findByQuery(visitorDetailQuery);
            return new AjaxResult(0,"查询成功" ,pageList );
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1, "查询失败,原因为"+e.getMessage(), null);
        }
    }
}
