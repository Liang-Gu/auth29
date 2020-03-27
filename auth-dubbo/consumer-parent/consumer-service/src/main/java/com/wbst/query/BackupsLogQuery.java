package com.wbst.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wbst.base.BaseQuery;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class BackupsLogQuery extends BaseQuery implements Serializable {

    //操作员
    private String operationName;

    //查询操作开始时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date operationStartTime;

    //查询操作结束时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date operationEndTime;

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public Date getOperationStartTime() {
        return operationStartTime;
    }

    public void setOperationStartTime(Date operationStartTime) {
        this.operationStartTime = operationStartTime;
    }

    public Date getOperationEndTime() {
        return operationEndTime;
    }

    public void setOperationEndTime(Date operationEndTime) {
        this.operationEndTime = operationEndTime;
    }
}
