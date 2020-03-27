package com.wbst.domain;

import java.io.Serializable;

//消息队列发送过来的消息的消息头
public class MessageTitle implements Serializable {
    //主键
    private Long id;

    //消息发送者， 主要指发送该消息的适配器
    private String sender;

    //消息来源系统，此处指Lenel系统
    private String sourceSystem;

    //事件分类
    private String messageType;

    //数据类型
    private String dataType;

    //操作类型
    private String actionName;

    //设备id（控制器编码+读卡器编码，用“-”连接）
    private String deviceOuterID;

    //消息流水号（sender+时间戳）
    private String messageNo;

    private SwipeCardEvent content=new SwipeCardEvent();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getDeviceOuterID() {
        return deviceOuterID;
    }

    public void setDeviceOuterID(String deviceOuterID) {
        this.deviceOuterID = deviceOuterID;
    }

    public String getMessageNo() {
        return messageNo;
    }

    public void setMessageNo(String messageNo) {
        this.messageNo = messageNo;
    }

    public SwipeCardEvent getContent() {
        return content;
    }

    public void setContent(SwipeCardEvent content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageTitle{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", sourceSystem='" + sourceSystem + '\'' +
                ", messageType='" + messageType + '\'' +
                ", dataType='" + dataType + '\'' +
                ", actionName='" + actionName + '\'' +
                ", deviceOuterID='" + deviceOuterID + '\'' +
                ", messageNo='" + messageNo + '\'' +
                ", content=" + content +
                '}';
    }
}
