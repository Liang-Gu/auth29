package com.wbst.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class WarningDataDetail implements Serializable {

    //主键
    private Long id;

    //异常数据员工编号
    private String personSn;

    //上次刷卡时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date lastedSwipeTime;


    //处理人
    private String handlePeople;


    //处理时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date handleTime= new Date();

    //处理意见
    private String abnormaDesc;

    //父ID
    private Long pid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLastedSwipeTime() {
        return lastedSwipeTime;
    }

    public void setLastedSwipeTime(Date lastedSwipeTime) {
        this.lastedSwipeTime = lastedSwipeTime;
    }

    public String getHandlePeople() {
        return handlePeople;
    }

    public void setHandlePeople(String handlePeople) {
        this.handlePeople = handlePeople;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getAbnormaDesc() {
        return abnormaDesc;
    }

    public void setAbnormaDesc(String abnormaDesc) {
        this.abnormaDesc = abnormaDesc;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPersonSn() {
        return personSn;
    }

    public void setPersonSn(String personSn) {
        this.personSn = personSn;
    }
}
