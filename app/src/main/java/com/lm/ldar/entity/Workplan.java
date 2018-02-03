package com.lm.ldar.entity;

import java.io.Serializable;

/**
 * 工作计划
 * Created by xieweikun on 2018/1/18.
 */

public class Workplan implements Serializable {

    private Long id;//序号
    private String name;//计划名称
    private int eid;//企业ID
    private int state;//状态
    private int nid;//命名规则ID
    private int sid;//泄漏标准ID
    private int pvid;//版本号ID

    public Workplan() {
    }

    public Workplan(Long id, String name, int eid, int state, int nid, int sid, int pvid) {
        this.id = id;
        this.name = name;
        this.eid = eid;
        this.state = state;
        this.nid = nid;
        this.sid = sid;
        this.pvid = pvid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getPvid() {
        return pvid;
    }

    public void setPvid(int pvid) {
        this.pvid = pvid;
    }
}
