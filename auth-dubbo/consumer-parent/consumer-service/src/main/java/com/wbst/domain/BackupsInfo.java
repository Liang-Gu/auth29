package com.wbst.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BackupsInfo {

    //主键
    private Long backupsId;


    //备份文件名
    private String backupsName;

    //备份路径
    private String backupsPath;

    //备份时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date backupsTime;

    //数据大小（M）
    private String backupsSize;


    //删除标识
    private Integer delFlag;



    public Long getBackupsId() {
        return backupsId;
    }

    public void setBackupsId(Long backupsId) {
        this.backupsId = backupsId;
    }

    public String getBackupsName() {
        return backupsName;
    }

    public void setBackupsName(String backupsName) {
        this.backupsName = backupsName;
    }

    public String getBackupsPath() {
        return backupsPath;
    }

    public void setBackupsPath(String backupsPath) {
        this.backupsPath = backupsPath;
    }

    public Date getBackupsTime() {
        return backupsTime;
    }

    public void setBackupsTime(Date backupsTime) {
        this.backupsTime = backupsTime;
    }

    public String getBackupsSize() {
        return backupsSize;
    }

    public void setBackupsSize(String backupsSize) {
        this.backupsSize = backupsSize;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

}
