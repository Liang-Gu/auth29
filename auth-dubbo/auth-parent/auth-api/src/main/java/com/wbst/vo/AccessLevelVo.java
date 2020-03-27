package com.wbst.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class AccessLevelVo implements Serializable {

    //权限编号
    private Integer accessLevelId;

    //通行权限名称
    private String accessLevelName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date effectiveTimeStart;

    //有效期结束时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date effectiveTimeEnd;

    ////权限时间状态
    private Integer accessLevelStatus;



    public String getAccessLevelName() {
        return accessLevelName;
    }

    public void setAccessLevelName(String accessLevelName) {
        this.accessLevelName = accessLevelName;
    }

    public Date getEffectiveTimeStart() {
        return effectiveTimeStart;
    }

    public void setEffectiveTimeStart(Date effectiveTimeStart) {
        this.effectiveTimeStart = effectiveTimeStart;
    }

    public Date getEffectiveTimeEnd() {
        return effectiveTimeEnd;
    }

    public void setEffectiveTimeEnd(Date effectiveTimeEnd) {
        this.effectiveTimeEnd = effectiveTimeEnd;
    }

    public Integer getAccessLevelStatus() {
        return accessLevelStatus;
    }

    public void setAccessLevelStatus(Integer accessLevelStatus) {
        this.accessLevelStatus = accessLevelStatus;
    }

    public Integer getAccessLevelId() {
        return accessLevelId;
    }

    public void setAccessLevelId(Integer accessLevelId) {
        this.accessLevelId = accessLevelId;
    }
}
