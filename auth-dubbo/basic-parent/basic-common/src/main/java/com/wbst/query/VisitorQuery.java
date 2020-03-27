package com.wbst.query;


import com.wbst.base.BaseQuery;

public class VisitorQuery extends BaseQuery {

    //姓名
    private String name;

    //单位
    private String company;

    //证件编号
    private String certificatesNum;

    //电话号码
    private String phone;

    //被访人姓名
    private String visitedName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCertificatesNum() {
        return certificatesNum;
    }

    public void setCertificatesNum(String certificatesNum) {
        this.certificatesNum = certificatesNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVisitedName() {
        return visitedName;
    }

    public void setVisitedName(String visitedName) {
        this.visitedName = visitedName;
    }
}
