package com.lm.ldar.entity;

import java.io.Serializable;

/**
 * 图片表
 * Created by xieweikun on 2018/1/15.
 */

public class PictureDownload implements Serializable {

    private Long id;//序号
    private String number;//图片编号
    private String name;//图片名称
    private String status;//状态 0 未编辑 1 已编辑
    private String createtime;//创建时间
    private String deviceinfo;//装置信息
    private String material; //物质信息
    private String position;//位置信息
    private int did;//装置ID
    private int aid;//区域ID
    private int eid;//企业ID
    private String elementname;//图片重命名
    private String pidnumber;//PID图号
    private int pvid;//图像版本号
    private String sketch;//草图地址

    public PictureDownload() {
    }

    public PictureDownload(Long id, String number, String name, String status, String createtime, String deviceinfo, String material, String position, int did, int aid, int eid, String elementname, String pidnumber, int pvid, String sketch) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.status = status;
        this.createtime = createtime;
        this.deviceinfo = deviceinfo;
        this.material = material;
        this.position = position;
        this.did = did;
        this.aid = aid;
        this.eid = eid;
        this.elementname = elementname;
        this.pidnumber = pidnumber;
        this.pvid = pvid;
        this.sketch = sketch;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getDeviceinfo() {
        return deviceinfo;
    }

    public void setDeviceinfo(String deviceinfo) {
        this.deviceinfo = deviceinfo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getElementname() {
        return elementname;
    }

    public void setElementname(String elementname) {
        this.elementname = elementname;
    }

    public String getPidnumber() {
        return pidnumber;
    }

    public void setPidnumber(String pidnumber) {
        this.pidnumber = pidnumber;
    }

    public int getPvid() {
        return pvid;
    }

    public void setPvid(int pvid) {
        this.pvid = pvid;
    }

    public String getSketch() {
        return sketch;
    }

    public void setSketch(String sketch) {
        this.sketch = sketch;
    }
}
