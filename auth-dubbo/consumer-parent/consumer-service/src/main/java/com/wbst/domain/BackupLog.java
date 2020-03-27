package com.wbst.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BackupLog {

    //记录主键
    private Long backupLogId;

    //操作员
    private String operationName;

    //操作时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date operationTime=new Date();

    //操作内容
    private String operationContent;

    //操作备注
    private String operationDesc;

    //删除标识
    private Integer delFlag;

    public BackupLog(){}

    public BackupLog( String operationName, Date operationTime, String operationContent, String operationDesc) {
        this.backupLogId = backupLogId;
        this.operationName = operationName;
        this.operationTime = operationTime;
        this.operationContent = operationContent;
        this.operationDesc = operationDesc;
    }

    public Long getBackupLogId() {
        return backupLogId;
    }

    public void setBackupLogId(Long backupLogId) {
        this.backupLogId = backupLogId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    public String getOperationDesc() {
        return operationDesc;
    }

    public void setOperationDesc(String operationDesc) {
        this.operationDesc = operationDesc;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
