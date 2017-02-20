package com.ys.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by yushi on 2017/2/14.
 */
@ConfigurationProperties(prefix = "hello")
public class HelloServiceProperties {

    private static final String MSG = "world";

    private String msg = MSG;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
