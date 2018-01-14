package com.lm.ldar.entity;

import java.io.Serializable;

/**
 * Created by fanshengyue on 2018/1/13.
 * 登录的用户信息实体类
 */

public class LoginUserEntity implements Serializable {
    private Long id;
    private String username;
    private String password;

    public LoginUserEntity(){}

    public LoginUserEntity(Long id,String username,String password){
        this.id=id;
        this.username=username;
        this.password=password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
