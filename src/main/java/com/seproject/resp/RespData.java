package com.seproject.resp;

//需要额外传递的data的基类
public class RespData {
    private String msg;

    public RespData() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RespData(String msg) {
        this.msg = msg;
    }
}
