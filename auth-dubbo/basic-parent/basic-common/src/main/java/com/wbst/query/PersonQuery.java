package com.wbst.query;

import com.wbst.base.BaseQuery;

public class PersonQuery extends BaseQuery {
    private String personName;
    private String personSn;
    private String personAuthSn;
    private String personDept;

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

    public String getPersonDept() {
        return personDept;
    }

    public void setPersonDept(String personDept) {
        this.personDept = personDept;
    }
}
