package com.lm.ldar.entity;

import java.io.Serializable;

/**
 * Created by xieweikun on 2018/1/14.
 */

public class Area implements Serializable{

    private Long id;//id
    private String number;//区域编号
    private String name;//区域名称
    private String createtime;//创建时间
    private int did;//装置ID
    private int eid;//企业ID
    private int valid;//状态

    public Area() {
    }

    public Area(Long id, String number, String name, String createtime, int did, int eid, int valid) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.createtime = createtime;
        this.did = did;
        this.eid = eid;
        this.valid = valid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }
}
