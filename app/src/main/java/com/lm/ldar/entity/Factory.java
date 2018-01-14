package com.lm.ldar.entity;

import java.io.Serializable;

/**
 * Created by xieweikun on 2018/1/14.
 */

public class Factory implements Serializable {

    private Long id;
    private String number;//厂区编号
    private String name;//厂区名称
    private String create_time;//创建时间
    private int valid;//状态
    private int eid;//企业ID

    public Factory() {
    }

    public Factory(Long id, String number, String name, String create_time, int valid, int eid) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.create_time = create_time;
        this.valid = valid;
        this.eid = eid;
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

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }
}
