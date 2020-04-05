package cn.brownqi.rest;

import cn.brownqi.utils.ObjectUtils;

public class Result {

    private Integer code;
    private String message;
    private Object data;

    private Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result ERROR(Integer code, String message){
        return new Result(code,message, ObjectUtils.emptyObject());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result OK(int code, String message, Object data){
        return new Result(code,message,data);
    }
}
