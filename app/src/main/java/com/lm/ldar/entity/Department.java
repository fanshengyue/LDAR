package com.lm.ldar.entity;

import java.io.Serializable;

/**
 * 部门表
 * Created by xieweikun on 2018/1/14.
 */

public class Department implements Serializable {
    private Long id;//部门ID
    private String number;//部门编号
    private String name;//部门名称
    private String createtime;//创建时间
    private int fid;//厂区ID
    private int eid;//企业ID
    private int valid;//状态

    public Department() {
    }

    public Department(Long id, String number, String name, String createtime, int fid, int eid, int valid) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.createtime = createtime;
        this.fid = fid;
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

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
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
