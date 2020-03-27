package com.wbst.query;

import com.wbst.base.BaseQuery;

import java.io.Serializable;
import java.util.Date;

public class AuthLogQuery extends BaseQuery implements Serializable {

    //操作员
    private String operationName;

    //操作对象
        private String actionObject;

    //查询开始时间
    private Date operationStartTime;

    //查询结束时间
    private Date operationEndTime;

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getActionObject() {
        return actionObject;
    }

    public void setActionObject(String actionObject) {
        this.actionObject = actionObject;
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
