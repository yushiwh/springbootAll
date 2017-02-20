package com.ys.service;

/**
 * Created by yushi on 2017/2/14.
 */
public class HelloService {
    private String msg;

    private String sayHello() {
        return "Hello" + msg;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
