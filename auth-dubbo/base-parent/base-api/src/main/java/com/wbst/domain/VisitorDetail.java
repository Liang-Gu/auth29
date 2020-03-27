package com.wbst.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 访客明细
 */
public class VisitorDetail implements Serializable {

    //主键
    private Long id;

    //父ID
    private Long pid;

    //申请编码
    private String applyNum;

    //被访不么
    private String visitedDept;

    //被访人员名字
    private String visitedName;

    //临时卡号
    private String temporaryCardNum;

    //访问开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date visitingTime;

    //离开时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date leaveTime;

    //访问状态
    private int status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(String applyNum) {
        this.applyNum = applyNum;
    }



    public String getVisitedName() {
        return visitedName;
    }

    public void setVisitedName(String visitedName) {
        this.visitedName = visitedName;
    }

    public String getTemporaryCardNum() {
        return temporaryCardNum;
    }

    public void setTemporaryCardNum(String temporaryCardNum) {
        this.temporaryCardNum = temporaryCardNum;
    }

    public Date getVisitingTime() {
        return visitingTime;
    }

    public void setVisitingTime(Date visitingTime) {
        this.visitingTime = visitingTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getVisitedDept() {
        return visitedDept;
    }

    public void setVisitedDept(String visitedDept) {
        this.visitedDept = visitedDept;
    }
}
