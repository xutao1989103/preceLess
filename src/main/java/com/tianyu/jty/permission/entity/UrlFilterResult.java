package com.tianyu.jty.permission.entity;

/**
 * Created by xtao on 2015/11/17.
 */
public class UrlFilterResult {
    private Integer code;
    private String msg;

    public static UrlFilterResult getResult(int statusCode){
        if(statusCode == 200){
            return new UrlFilterResult(200, "OK");
        }else if(statusCode == 400){
            return new UrlFilterResult(400, "Bad Request");
        }else if(statusCode == 401){
            return new UrlFilterResult(401, "Unauthorized");
        }else if(statusCode == 404){
            return new UrlFilterResult(404, "Not Found");
        }else {
            return new UrlFilterResult(200, "OK");
        }
    }

    public UrlFilterResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "UrlFilterResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
