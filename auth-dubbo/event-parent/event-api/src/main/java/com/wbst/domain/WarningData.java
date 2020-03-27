package com.wbst.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 异常实体类
 */
public class WarningData implements Serializable {
    //员工ID
    private Long warningId;

    //员工头像
    private String personPhoto;

    //员工姓名
    private String personName;

    //员工编号
    private String personSn;

    //授权编号(芯片卡号)
    private String personAuthSn;

    //电话号码
    private String personPhone;

    //被访次数
    private Integer personByvistNum;

    //部门
    private String personDept;

    //通行权限个数
    private Integer personPassAuth;

    //最近刷卡时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date lastedSwipeTime;

    //异常时长
    private Integer abnormalDuration;

    //异常状态  0 未处理 1 处理
    private Integer   abnormalStatus;

    //异常处理备注
    private String abnormalDesc;

    //连续警告次数
    private Integer alarmNum;

    //总警告次数
    private Integer totalAlarmNum;

    //删除标识
    private Integer delFlag;

    //处理详情集合
    private List<WarningDataDetail> warningDataDetails;

    public Long getWarningId() {
        return warningId;
    }

    public void setWarningId(Long warningId) {
        this.warningId = warningId;
    }

    public String getPersonPhoto() {
        return personPhoto;
    }

    public void setPersonPhoto(String personPhoto) {
        this.personPhoto = personPhoto;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonSn() {
        return personSn;
    }

    public void setPersonSn(String personSn) {
        this.personSn = personSn;
    }

    public String getPersonAuthSn() {
        return personAuthSn;
    }

    public void setPersonAuthSn(String personAuthSn) {
        this.personAuthSn = personAuthSn;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public Integer getPersonByvistNum() {
        return personByvistNum;
    }

    public void setPersonByvistNum(Integer personByvistNum) {
        this.personByvistNum = personByvistNum;
    }

    public String getPersonDept() {
        return personDept;
    }

    public void setPersonDept(String personDept) {
        this.personDept = personDept;
    }

    public Integer getPersonPassAuth() {
        return personPassAuth;
    }

    public void setPersonPassAuth(Integer personPassAuth) {
        this.personPassAuth = personPassAuth;
    }

    public Date getLastedSwipeTime() {
        return lastedSwipeTime;
    }

    public void setLastedSwipeTime(Date lastedSwipeTime) {
        this.lastedSwipeTime = lastedSwipeTime;
    }

    public Integer getAbnormalDuration() {
        return abnormalDuration;
    }

    public void setAbnormalDuration(Integer abnormalDuration) {
        this.abnormalDuration = abnormalDuration;
    }

    public Integer getAbnormalStatus() {
        return abnormalStatus;
    }

    public void setAbnormalStatus(Integer abnormalStatus) {
        this.abnormalStatus = abnormalStatus;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getAbnormalDesc() {
        return abnormalDesc;
    }

    public void setAbnormalDesc(String abnormalDesc) {
        this.abnormalDesc = abnormalDesc;
    }

    public Integer getAlarmNum() {
        return alarmNum;
    }

    public void setAlarmNum(Integer alarmNum) {
        this.alarmNum = alarmNum;
    }

    public Integer getTotalAlarmNum() {
        return totalAlarmNum;
    }

    public void setTotalAlarmNum(Integer totalAlarmNum) {
        this.totalAlarmNum = totalAlarmNum;
    }

    public List<WarningDataDetail> getWarningDataDetails() {
        return warningDataDetails;
    }

    public void setWarningDataDetails(List<WarningDataDetail> warningDataDetails) {
        this.warningDataDetails = warningDataDetails;
    }
}
