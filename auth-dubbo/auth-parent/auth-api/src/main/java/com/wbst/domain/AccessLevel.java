package com.wbst.domain;

import java.io.Serializable;
import java.util.Date;

public class AccessLevel implements Serializable {

    //主键
    private Integer levelId;

    //通行权限编号
    private Integer accessLevelId;

    //通行权限名称
    private String accessLevelName;

    //通行权限描述
    private String accessLevelDesc;

    //控制器名称
    private String controllerName;

    //控制器编号
    private Integer controllerId;

    //读卡器名称
    private String readerName;

    //读卡器编号
    private Integer readerId;


    //
    private String validTime;


    //创建时间
    private Date createTime=new Date();

    //删除标识
    private Integer delFlag;


    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getAccessLevelId() {
        return accessLevelId;
    }

    public void setAccessLevelId(Integer accessLevelId) {
        this.accessLevelId = accessLevelId;
    }

    public String getAccessLevelName() {
        return accessLevelName;
    }

    public void setAccessLevelName(String accessLevelName) {
        this.accessLevelName = accessLevelName;
    }

    public String getAccessLevelDesc() {
        return accessLevelDesc;
    }

    public void setAccessLevelDesc(String accessLevelDesc) {
        this.accessLevelDesc = accessLevelDesc;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public Integer getControllerId() {
        return controllerId;
    }

    public void setControllerId(Integer controllerId) {
        this.controllerId = controllerId;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
