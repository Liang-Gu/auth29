package com.wbst.query;

import com.wbst.base.BaseQuery;

public class WarningDataQuery extends BaseQuery {

    //部门
    private String deptName;

    //员工姓名
    private String personName;

    //员工编号
    private String personNum;


    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonNum() {
        return personNum;
    }

    public void setPersonNum(String personNum) {
        this.personNum = personNum;
    }
}
