package com.wbst.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.zookeeper.data.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 授权操作日志记录实体类
 */
public class AuthLog implements Serializable {

    //主键
    private Long id;

    //操作时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date operationTime;

    //操作员
    private String operationName;

    //操作对象
    private String   actionObject;

    //内容
    private String operationContent;

    //备注
    private String operationDesc;



    public Long getId() {
        return id;
    }

    public AuthLog setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public AuthLog setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
        return this;
    }

    public String getOperationName() {
        return operationName;
    }

    public AuthLog setOperationName(String operationName) {
        this.operationName = operationName;
        return this;
    }

    public String getActionObject() {
        return actionObject;
    }

    public AuthLog setActionObject(String actionObject) {
        this.actionObject = actionObject;
        return this;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public AuthLog setOperationContent(String operationContent) {
        this.operationContent = operationContent;
        return this;
    }

    public String getOperationDesc() {
        return operationDesc;
    }

    public AuthLog setOperationDesc(String operationDesc) {
        this.operationDesc = operationDesc;
        return this;
    }
}
