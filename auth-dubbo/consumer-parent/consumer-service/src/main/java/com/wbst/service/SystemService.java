package com.wbst.service;

import com.wbst.domain.BackupLog;
import com.wbst.domain.BackupsInfo;
import com.wbst.dto.BackupDeleteDto;
import com.wbst.query.BackupsInfoQuery;
import com.wbst.query.BackupsLogQuery;
import com.wbst.util.PageList;

public interface SystemService {
    //系统数据备份
    void systembackups(String operationDesc);

    //数据恢复
    void systemRestore(String backupsPath,String backupsName);

    //查询所有备份记录
    PageList findAll(BackupsInfoQuery backupsInfoQuery);

    //备份文件删除
    void backupDelete(BackupDeleteDto backupDeleteDto);

    //查询操作日志
    PageList<BackupLog> findAllBackupLog(BackupsLogQuery backupsLogQuery);

    //设置自动备份周期
    void automatic_model(Integer automaticModel, Integer cycle);

    //查询自动备份corn表达式
    String findAutomaticModel();

}
