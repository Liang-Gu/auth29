package com.wbst.domain;

import java.io.Serializable;

public class AuthChange implements Serializable {
    //员工姓名
    private String personName;

    //部门
    private String deptName;

    //授权变更次数
    private Integer changeTime;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Integer changeTime) {
        this.changeTime = changeTime;
    }
}
