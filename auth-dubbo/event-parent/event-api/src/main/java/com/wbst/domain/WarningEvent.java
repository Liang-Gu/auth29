package com.wbst.domain;

import java.util.Date;

/**
 * 警告事件实体类
 */
public class WarningEvent {
    //主键
    private Long id;

    //芯片卡卡号
    private String cardNumber;

    //员工工号
    private String personCode;

    //持卡人
    private String cardholder;

    //刷卡时间
    private Date time;

    //控制器编码
    private Integer controllerId;

    //读卡器编码
    private Integer readerId;

    //通行结果1通过，2无效卡，3授权过期
    private Integer accessResult;

    //报警类型
    private String dataType;

    //
}
