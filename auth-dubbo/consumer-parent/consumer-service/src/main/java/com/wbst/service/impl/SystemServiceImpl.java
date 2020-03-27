package com.wbst.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wbst.Util.ByteUtil;
import com.wbst.Util.DataBaseUtils;
import com.wbst.constant.SystemConstant;
import com.wbst.domain.BackupLog;
import com.wbst.domain.BackupsInfo;
import com.wbst.dto.BackupDeleteDto;
import com.wbst.mapper.SystemMapper;
import com.wbst.query.BackupsInfoQuery;
import com.wbst.query.BackupsLogQuery;
import com.wbst.service.SystemService;
import com.wbst.util.PageList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly =true,propagation = Propagation.SUPPORTS)
public class SystemServiceImpl implements SystemService {
    @Resource
    private SystemMapper systemMapper;

    //系统数据备份
    @Override
    @Transactional
    public void systembackups(String operationDesc) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        //数据备份
        DataBaseUtils.exportDatabaseTool(SystemConstant.SYSTEMIP,SystemConstant.USERNAME,SystemConstant.PASSWORD,SystemConstant.SAVEPATH,format+".sql",SystemConstant.DATABASENAME);
        //保存备份信息
        BackupsInfo backupsInfo = new BackupsInfo();
        //设置操作日志文件名
        backupsInfo.setBackupsName(format+".sql");
        backupsInfo.setBackupsPath(SystemConstant.SAVEPATH);
        backupsInfo.setBackupsTime(date);
        File file = new File(SystemConstant.SAVEPATH + "/" + format + ".sql");
        if (file.exists() && file.isFile()){
            long length = file.length();
            //获取文件大小
            backupsInfo.setBackupsSize(ByteUtil.getPrintSize(length));
        }
        //保存备份信息
        systemMapper.saveBackups(backupsInfo);

        //保存操作日志
        BackupLog backupLog = new BackupLog();
        //操作内容
        String operationContent="完成系统数据备份,备份文件名："+format+".sql";
        backupLog.setOperationContent(operationContent);
        backupLog.setOperationDesc(operationDesc);
        backupLog.setOperationName("开发测试");
        backupLog.setOperationTime(date);
        systemMapper.saveBackupsLog(backupLog);

    }

    @Override
    @Transactional
    public void systemRestore(String backupsPath,String backupsName) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        //数据恢复
        boolean restore = DataBaseUtils.restore(SystemConstant.SYSTEMIP, SystemConstant.USERNAME, SystemConstant.PASSWORD, SystemConstant.DATABASENAME, backupsPath);
        //保存还原操作记录
        BackupLog backupLog = new BackupLog();
        String operationContent="还原"+backupsName+"备份文件";
        backupLog.setOperationContent(operationContent);
        backupLog.setOperationName("开发测试");
        backupLog.setOperationTime(date);
        systemMapper.saveBackupsLog(backupLog);
    }

    /**
     * 查询所有的备份记录
     * @return
     */

    @Override
    public PageList<BackupsInfo> findAll(BackupsInfoQuery backupsInfoQuery) {
      Page<BackupsInfo> page= PageHelper.startPage(backupsInfoQuery.getCurrentPage(),backupsInfoQuery.getPageSize());
       List<BackupsInfo> backupsInfos= systemMapper.findAll(backupsInfoQuery);
        return new PageList<>(page.getTotal(),page.getPages(),backupsInfos);
    }

    /**
     * 备份文件删除
     * @param backupDeleteDto
     */
    @Override
    @Transactional
    public void backupDelete(BackupDeleteDto backupDeleteDto) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        //删除数据库记录(逻辑删除)
        systemMapper.backupDelete(backupDeleteDto.getBackupsId());

        //删除磁盘上的文件
        //拼接路径
        String path=backupDeleteDto.getBackupsPath()+"/"+backupDeleteDto.getBackupsName();
        File file = new File(path);
        boolean file1 = file.isFile();
        if (file1){
            file.delete();
        }

        //保存操作日志
        BackupLog backupLog = new BackupLog();
        String operationContent="删除"+backupDeleteDto.getBackupsName()+"备份文件";
        backupLog.setOperationContent(operationContent);
        backupLog.setOperationName("开发测试");
        backupLog.setOperationTime(date);
        backupLog.setOperationDesc(backupDeleteDto.getOperationDesc());
        systemMapper.saveBackupsLog(backupLog);
    }

    /**
     *  查询所有操作日志
     * @return
     */
    @Override
    public PageList<BackupLog> findAllBackupLog(BackupsLogQuery backupsLogQuery) {
        Page<BackupLog> page = PageHelper.startPage(backupsLogQuery.getCurrentPage(), backupsLogQuery.getPageSize());
       List<BackupLog> backupLogs= systemMapper.findAllBackupLog(backupsLogQuery);
        return new PageList<>(page.getTotal(),page.getPages(),backupLogs);
    }

    //设置自动备份周期
    @Override
    public void automatic_model(Integer automaticModel, Integer cycle) {
        //判断自动备份类型
        if(automaticModel==1){//固定周期
            //清空自动备份周期设置表格
            systemMapper.deleteAutomaticModel();
            String corn="0 0 0 1/"+cycle+" * ?";
            systemMapper.saveAutomaticModel(corn,automaticModel);
        }else {//固定日期
            //清空自动备份周期设置表格
            systemMapper.deleteAutomaticModel();
            String corn="0 0 0 "+cycle+" * ? ";
            systemMapper.saveAutomaticModel(corn,automaticModel);
        }
    }

    @Override
    public String findAutomaticModel() {
        return systemMapper.findAutomaticModel();
    }


}
