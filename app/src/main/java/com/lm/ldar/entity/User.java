package com.lm.ldar.entity;

import java.io.Serializable;

/**
 * Created by fanshengyue on 2018/1/13.
 */

public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String truename;
    private String lastlogintime;
    private String lastloginip;
    private int valid;//用户状态 0--禁用，1--启用
    private String createtime;
    private Long eid;//企业id
    private int logintimes;//登录次数
    private String phone;
    private String email;
    private String usergroups;

    public User(){}
    public User(Long id,String username,String password,String truename,String lastlogintime,String lastloginip,int valid,
                String createtime,Long eid,int logintimes,String phone,String email,String usergroups){
        this.id=id;
        this.username=username;
        this.password=password;
        this.truename=truename;
        this.lastlogintime=lastlogintime;
        this.lastloginip=lastloginip;
        this.valid=valid;
        this.createtime=createtime;
        this.eid=eid;
        this.logintimes=logintimes;
        this.phone=phone;
        this.email=email;
        this.usergroups=usergroups;
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

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(String lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getLastloginip() {
        return lastloginip;
    }

    public void setLastloginip(String lastloginip) {
        this.lastloginip = lastloginip;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public int getLogintimes() {
        return logintimes;
    }

    public void setLogintimes(int logintimes) {
        this.logintimes = logintimes;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsergroups() {
        return usergroups;
    }

    public void setUsergroups(String usergroups) {
        this.usergroups = usergroups;
    }


}
