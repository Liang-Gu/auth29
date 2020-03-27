package com.wbst.query;


import com.wbst.util.AuthUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AuthQuery implements Serializable {
    //员工对应的员工卡芯片号
    private List<String> cardNums=new ArrayList<>();

    //通行权限编号集合
    private List<AuthUtil> auths=new ArrayList<>();

    public List<String> getCardNums() {
        return cardNums;
    }

    public void setCardNums(List<String> cardNums) {
        this.cardNums = cardNums;
    }

    public List<AuthUtil> getAuths() {
        return auths;
    }

    public void setAuths(List<AuthUtil> auths) {
        this.auths = auths;
    }
}
