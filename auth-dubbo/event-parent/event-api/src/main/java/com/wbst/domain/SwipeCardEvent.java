package com.wbst.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 刷卡/警告时间实体类
 */
public class SwipeCardEvent implements Serializable {

    //主键
    private Long id;

    //芯片卡卡号
    private String cardNumber;

    //员工工号
    private String personCode;

    //持卡人
    private String cardholder;

    //刷卡时间
    private Date time;

    //控制器编码
    private Integer controllerId;

    //读卡器编码
    private Integer readerId;

    //通行结果1通过，2无效卡，3授权过期
    private Integer accessResult;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getAccessResult() {
        return accessResult;
    }

    public void setAccessResult(Integer accessResult) {
        this.accessResult = accessResult;
    }


    public Integer getControllerId() {
        return controllerId;
    }

    public void setControllerId(Integer controllerId) {
        this.controllerId = controllerId;
    }

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    @Override
    public String toString() {
        return "SwipeCardEvent{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", personCode='" + personCode + '\'' +
                ", cardholder='" + cardholder + '\'' +
                ", time=" + time +
                ", controllerId=" + controllerId +
                ", readerId=" + readerId +
                ", accessResult=" + accessResult +
                '}';
    }
}

