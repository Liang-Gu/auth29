package com.wbst.domain;

import java.io.Serializable;

public class NoSwipingCard implements Serializable {

    //姓名
    private String personName;

    //头像地址
    private String personPhoto;

    //部门
    private String deptName;

    //未刷卡天数
    private Integer noSwipingDays;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonPhoto() {
        return personPhoto;
    }

    public void setPersonPhoto(String personPhoto) {
        this.personPhoto = personPhoto;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getNoSwipingDays() {
        return noSwipingDays;
    }

    public void setNoSwipingDays(Integer noSwipingDays) {
        this.noSwipingDays = noSwipingDays;
    }
}
