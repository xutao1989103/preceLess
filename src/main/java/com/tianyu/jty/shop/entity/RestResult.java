package com.tianyu.jty.shop.entity;

/**
 * Created by xtao on 2015/11/22.
 */
public class RestResult {
    private Integer code;
    private String msg;
    private Object content;

    public RestResult() {
    }

    public RestResult(Integer code, String msg, Object content) {
        this.code = code;
        this.msg = msg;
        this.content = content;
    }

    public static RestResult instance(){
        return new RestResult();
    }

    public static RestResult success(){
        return new RestResult(200, "success", null);
    }

    public static RestResult fail(){
        return new RestResult(400, "failure", null);
    }

    public RestResult withCode(Integer code){
        this.code = code;
        return this;
    }

    public RestResult withMsg(String msg){
        this.msg = msg;
        return this;
    }

    public RestResult withContent(Object content){
        this.content = content;
        return this;
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

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
