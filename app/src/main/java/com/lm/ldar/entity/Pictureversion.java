package com.lm.ldar.entity;

import java.io.Serializable;

/**
 * 图像版本表
 * Created by xieweikun on 2018/1/14.
 */

public class Pictureversion implements Serializable{

    private Long id;//图像版本ID
    private String name;//版本号
    private String introduction;//版本描述
    private String createtime;//创建时间
    private int eid;//企业ID
    private int uid;//添加人ID
    private int pvid;//继承图像版本ID
    private int type;//建档类型

    public Pictureversion() {
    }

    public Pictureversion(Long id, String name, String introduction, String createtime, int eid, int uid, int pvid, int type) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
        this.createtime = createtime;
        this.eid = eid;
        this.uid = uid;
        this.pvid = pvid;
        this.type = type;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPvid() {
        return pvid;
    }

    public void setPvid(int pvid) {
        this.pvid = pvid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
