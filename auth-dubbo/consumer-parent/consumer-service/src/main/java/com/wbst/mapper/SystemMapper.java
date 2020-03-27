package com.wbst.mapper;

import com.wbst.domain.BackupLog;
import com.wbst.domain.BackupsInfo;
import com.wbst.query.BackupsInfoQuery;
import com.wbst.query.BackupsLogQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemMapper {

    //备份信息保存
    void saveBackups(BackupsInfo backupsInfo);

    //查询 所有的备份记录
    List<BackupsInfo> findAll(BackupsInfoQuery backupsInfoQuery);

    //保存操作日志
    void saveBackupsLog(BackupLog backupLog);

    //删除数据库记录
    void backupDelete(Long backupsId);

    //查询所有操作日志记录
    List<BackupLog> findAllBackupLog(BackupsLogQuery backupsLogQuery);

    //清空自动备份周期设置表格
    void deleteAutomaticModel();

    //设置自动备份周期
    void saveAutomaticModel(@Param("corn") String corn,@Param("automaticModel") Integer automaticModel);

   // 查询自动备份corn表达式
    String findAutomaticModel();

}
