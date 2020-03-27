package com.wbst.util;

public class AjaxResult {
    private int code = 0;
    private String msg;
    private Object data;

    public AjaxResult() {
    }

    public AjaxResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.code=1;
        this.msg = msg;
    }
}
