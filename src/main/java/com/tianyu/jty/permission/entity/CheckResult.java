package com.tianyu.jty.permission.entity;

/**
 * Created by xtao on 2015/11/20.
 */
public class CheckResult {
    private boolean pass;
    private String msg;

    public CheckResult() {
    }

    public CheckResult(boolean pass, String msg) {
        this.pass = pass;
        this.msg = msg;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
