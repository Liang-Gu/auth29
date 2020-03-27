package com.wbst.controller;

import com.wbst.domain.BackupLog;
import com.wbst.domain.BackupsInfo;
import com.wbst.dto.BackupDeleteDto;
import com.wbst.query.BackupsInfoQuery;
import com.wbst.query.BackupsLogQuery;
import com.wbst.service.SystemService;
import com.wbst.util.AjaxResult;
import com.wbst.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/system")
public class SysytemController {

    @Autowired
    private SystemService systemService;

    //系统备份
    @RequestMapping(value = "/systemBackups",method = RequestMethod.GET)
    public AjaxResult systemBackups(@RequestParam("operationDesc") String operationDesc){
        try {
            systemService.systembackups(operationDesc);
            return new AjaxResult(0,"备份成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1,"备份失败",null);
        }
    }

    /**
     * 数据备份恢复
     * @return
     */
    @RequestMapping(value = "/systemRestore",method = RequestMethod.GET)
    public  AjaxResult systemRestore(@RequestParam("backupsPath")String backupsPath,@RequestParam("backupsName")String backupsName){
        try {
            systemService.systemRestore(backupsPath,backupsName);
            return new AjaxResult(0,"备份还原成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1,"备份还原失败",null);
        }
    }

    //查询所有的备份
    @RequestMapping(value = "/findAll",method = RequestMethod.POST)
    public AjaxResult findAll(@RequestBody BackupsInfoQuery backupsInfoQuery){
        try {
            PageList pageList= systemService.findAll(backupsInfoQuery);
            return new AjaxResult(0,"查询成功",pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1,"查询失败",null);
        }
    }


    //删除备份
    @RequestMapping(value = "/backupDelete",method = RequestMethod.POST)
    public AjaxResult backupDelete(@RequestBody BackupDeleteDto backupDeleteDto){
        try {
            systemService.backupDelete(backupDeleteDto);
            return new AjaxResult(0,"备份删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(0,"备份删除失败，原因为："+e.getMessage(),null);
        }
    }

    //日志记录查询
    @RequestMapping(value = "/findAllBackupLog",method = RequestMethod.POST)
    public AjaxResult findAllBackupLog(@RequestBody BackupsLogQuery backupsLogQuery){
        try {
            PageList<BackupLog> pageList=  systemService.findAllBackupLog(backupsLogQuery);
            return new AjaxResult(0,"查询成功",pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(0,"查询失败",null);
        }
    }


    //设置自动备份模式
    @RequestMapping(value = "/automatic_model",method = RequestMethod.GET)
    public  AjaxResult  automatic_model(@RequestParam("automaticModel") Integer automaticModel,@RequestParam("cycle")Integer cycle){
        try {
            systemService.automatic_model(automaticModel,cycle);
            return new AjaxResult(0,"设置成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(0,"设置失败，原因为："+e.getMessage(),null);
        }
    }
}
