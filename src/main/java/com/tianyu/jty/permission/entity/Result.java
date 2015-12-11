package com.tianyu.jty.permission.entity;

/**
 * Created by xtao on 2015/11/19.
 */
public class Result {
    private String status;
    private LocationResult result;

    public Result() {

    }

    public Result(String status, LocationResult result) {
        this.status = status;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocationResult getResult() {
        return result;
    }

    public void setResult(LocationResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status='" + status + '\'' +
                ", result=" + result +
                '}';
    }
}
