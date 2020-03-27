package com.wbst.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 授权实体类
 */

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Auth implements Serializable {

    //主键ID
    private Long id;

    //员工芯片卡号
    private String cardNum;

    //通行权限编号
    private Integer accessLevelId;

    //有效期开始时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date effectiveTimeStart;

    //有效期结束时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date effectiveTimeEnd;


    //权限时间状态
    private Integer accessLevelStatus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public Integer getAccessLevelId() {
        return accessLevelId;
    }

    public void setAccessLevelId(Integer accessLevelId) {
        this.accessLevelId = accessLevelId;
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
}
