package com.wbst.query;

import com.wbst.base.BaseQuery;

import java.io.Serializable;
import java.util.Date;

public class BackupsInfoQuery extends BaseQuery implements Serializable {

    //备份文件名称
    private String backupsName;

    //操作人姓名
    private String operationName;

    //开始时间
    private Date backupsStartTime;

    //结束时间
    private Date backupsEndTime;


    public String getBackupsName() {
        return backupsName;
    }

    public void setBackupsName(String backupsName) {
        this.backupsName = backupsName;
    }

    public Date getBackupsStartTime() {
        return backupsStartTime;
    }

    public void setBackupsStartTime(Date backupsStartTime) {
        this.backupsStartTime = backupsStartTime;
    }

    public Date getBackupsEndTime() {
        return backupsEndTime;
    }

    public void setBackupsEndTime(Date backupsEndTime) {
        this.backupsEndTime = backupsEndTime;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}
