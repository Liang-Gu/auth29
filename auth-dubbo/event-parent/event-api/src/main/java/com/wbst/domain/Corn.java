package com.wbst.domain;

import java.io.Serializable;

public class Corn implements Serializable {
    //id
    private Integer i;

    //corn表达式
    private String cornString;

    //报警时长
    private Integer abnormalDuration;

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public String getCornString() {
        return cornString;
    }

    public void setCornString(String cornString) {
        this.cornString = cornString;
    }

    public Integer getAbnormalDuration() {
        return abnormalDuration;
    }

    public void setAbnormalDuration(Integer abnormalDuration) {
        this.abnormalDuration = abnormalDuration;
    }
}
