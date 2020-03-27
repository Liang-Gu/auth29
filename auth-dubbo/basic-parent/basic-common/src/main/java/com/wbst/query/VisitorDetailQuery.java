package com.wbst.query;


import com.wbst.base.BaseQuery;

public class VisitorDetailQuery extends BaseQuery {
    //访客姓名
    private String visitorName;

    //被访人
    private String visitedName;

    //申请编号
    private String applyNum;

    //临时卡号
    private String temporaryCardNum ;

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitedName() {
        return visitedName;
    }

    public void setVisitedName(String visitedName) {
        this.visitedName = visitedName;
    }

    public String getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(String applyNum) {
        this.applyNum = applyNum;
    }

    public String getTemporaryCardNum() {
        return temporaryCardNum;
    }

    public void setTemporaryCardNum(String temporaryCardNum) {
        this.temporaryCardNum = temporaryCardNum;
    }
}
