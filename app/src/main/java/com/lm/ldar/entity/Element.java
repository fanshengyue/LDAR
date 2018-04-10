package com.lm.ldar.entity;

import java.io.Serializable;

/**
 * Created by fanshengyue on 2018/3/23.
 */

public class Element implements Serializable {
    private Long id;
    private String type;//密封点类型
    private String datetime;//创建时间
    private Long did;//装置id
    private Long aid;//区域id
    private Long eid;//企业id
    private Long pid;//图片id
    private String text;//密封点编号
    private String method;//检测方法
    private String number;//组件编码
    private String updatetime;//更新时间
    private String isreach;//是否可达，1可达，2不可
    private String pidnumber;//PID图号
    private Long pvid;//图像版本ID

    public Element(){}
    public Element(Long id,String type,String datetime,Long did,Long aid,Long eid,Long pid,String text,String method,String number,
                   String updatetime,String isreach,String pidnumber,Long pvid){
        this.id=id;
        this.type=type;
        this.datetime=datetime;
        this.did=did;
        this.aid=aid;
        this.eid=eid;
        this.pid=pid;
        this.text=text;
        this.method=method;
        this.number=number;
        this.updatetime=updatetime;
        this.isreach=isreach;
        this.pidnumber=pidnumber;
        this.pvid=pvid;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getIsreach() {
        return isreach;
    }

    public void setIsreach(String isreach) {
        this.isreach = isreach;
    }

    public String getPidnumber() {
        return pidnumber;
    }

    public void setPidnumber(String pidnumber) {
        this.pidnumber = pidnumber;
    }

    public Long getPvid() {
        return pvid;
    }

    public void setPvid(Long pvid) {
        this.pvid = pvid;
    }
}
