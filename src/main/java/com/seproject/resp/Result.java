package com.seproject.resp;

//所有请求均返回该类的一个对象
public class Result {
    private String msg;
    private Object data;
    private boolean success;

    public static Result success() {
        return new Result("请求成功", null, true);
    }
    public static Result success(Object data) {
        return new Result("请求成功", data, true);
    }

    public static Result error() {
        return new Result("请求失败", null, false);
    }
    public static Result error(Object data) {
        return new Result("请求失败", data, false);
    }

    public Result(String msg, Object data, boolean success) {
        this.msg = msg;
        this.data = data;
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Result(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result() {
    }
}
