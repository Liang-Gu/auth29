package com.wbst.util;

public class LenelAuthUtil {
    //卡授权号
    private String badgeID;

    //通行级别ID
    private String accessLevelID;

    //启用时间Yyyy-mm-dd hh:MM:dd
    private String startTime;

    //失效时间Yyyy-mm-dd hh:MM:dd
    private String endTime;

    //动作
    private Integer actionID;

    public String getBadgeID() {
        return badgeID;
    }

    public void setBadgeID(String badgeID) {
        this.badgeID = badgeID;
    }

    public String getAccessLevelID() {
        return accessLevelID;
    }

    public void setAccessLevelID(String accessLevelID) {
        this.accessLevelID = accessLevelID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getActionID() {
        return actionID;
    }

    public void setActionID(Integer actionID) {
        this.actionID = actionID;
    }
}
