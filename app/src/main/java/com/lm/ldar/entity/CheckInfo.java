package com.lm.ldar.entity;

import java.io.Serializable;

/**
 * Created by fanshengyue on 2018/3/19.
 */
public class CheckInfo implements Serializable {
    private Long id;//序号
    private String date;//日期
    private String time;//时间
    private String medium;//媒介
    private String type;//组件类型
    private String tag;//组件编号
    private String pbvalue;//背景检测浓度
    private String pbunit;//
    private String pbstate;//
    private String pcvalue;//仪器检测浓度(检测最大值)
    private String pcunit;//
    private String pcstate;//
    private String fbvalue;//背景测浓度
    private String fbunit;//
    private String fbstate;//
    private String fcvalue;//仪器检测浓度,上次净检测值
    private String fcunit;//
    private String fcstate;//
    private String define;//泄漏定义值

    private Long uid;//用户id
    private Long insid;//仪器id
    private Long did;//装置id
    private Long aid;//子区域id
    private Long tid;//任务taskuserid
    private Long wid;//计划id
    private Long eleid;//标点id

    private double days;//运行天数
    private double horus;//日均运行小时数
    private int isleak;//是否泄露
    private double leakagerate;//泄漏量ppm
    private double leakageratepjxs;//平均排放系数，泄漏量ppm
    private String det;//FID   PID，检测方式
    private String leak;//是否泄漏
    private String leaksource;//泄露源
    private String repairmethod;//维修方式
    private String originalfcvalue;//
    private Long pvid;//图像版本ID
    private String checkpeople;//检测人员
    public CheckInfo(){}

    public CheckInfo(Long id,String date,String time,String medium,String type,String tag,String pbvalue,String pbunit,String pbstate,
                     String pcvalue,String pcunit,String pcstate,String fbvalue,String fbunit,String fbstate,String fcvalue,String fcunit,
                     String fcstate,String define,Long uid,Long insid,Long did,Long aid,Long tid,Long wid,Long eleid,double days,double horus,
                     int isleak,double leakagerate,double leakageratepjxs,String det,String leak,String leaksource,String repairmethod,
                     String originalfcvalue,Long pvid,String checkpeople){
        this.id=id;
        this.date=date;
        this.time=time;
        this.medium=medium;
        this.type=type;
        this.tag=tag;
        this.pbvalue=pbvalue;
        this.pbunit=pbunit;
        this.pbstate=pbstate;
        this.pcvalue=pcvalue;
        this.pcunit=pcunit;
        this.pcstate=pcstate;
        this.fbvalue=fbvalue;
        this.fbunit=fbunit;
        this.fbstate=fbstate;
        this.fcvalue=fcvalue;
        this.fcunit=fcunit;
        this.fcstate=fcstate;
        this.define=define;
        this.uid=uid;
        this.insid=insid;
        this.did=did;
        this.aid=aid;
        this.tid=tid;
        this.wid=wid;
        this.eleid=eleid;
        this.days=days;
        this.horus=horus;
        this.isleak=isleak;
        this.leakagerate=leakagerate;
        this.leakageratepjxs=leakageratepjxs;
        this.det=det;
        this.leak=leak;
        this.leaksource=leaksource;
        this.repairmethod=repairmethod;
        this.originalfcvalue=originalfcvalue;
        this.pvid=pvid;
        this.checkpeople=checkpeople;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPbvalue() {
        return pbvalue;
    }

    public void setPbvalue(String pbvalue) {
        this.pbvalue = pbvalue;
    }

    public String getPbunit() {
        return pbunit;
    }

    public void setPbunit(String pbunit) {
        this.pbunit = pbunit;
    }

    public String getPbstate() {
        return pbstate;
    }

    public void setPbstate(String pbstate) {
        this.pbstate = pbstate;
    }

    public String getPcvalue() {
        return pcvalue;
    }

    public void setPcvalue(String pcvalue) {
        this.pcvalue = pcvalue;
    }

    public String getPcunit() {
        return pcunit;
    }

    public void setPcunit(String pcunit) {
        this.pcunit = pcunit;
    }

    public String getPcstate() {
        return pcstate;
    }

    public void setPcstate(String pcstate) {
        this.pcstate = pcstate;
    }

    public String getFbvalue() {
        return fbvalue;
    }

    public void setFbvalue(String fbvalue) {
        this.fbvalue = fbvalue;
    }

    public String getFbunit() {
        return fbunit;
    }

    public void setFbunit(String fbunit) {
        this.fbunit = fbunit;
    }

    public String getFbstate() {
        return fbstate;
    }

    public void setFbstate(String fbstate) {
        this.fbstate = fbstate;
    }

    public String getFcvalue() {
        return fcvalue;
    }

    public void setFcvalue(String fcvalue) {
        this.fcvalue = fcvalue;
    }

    public String getFcunit() {
        return fcunit;
    }

    public void setFcunit(String fcunit) {
        this.fcunit = fcunit;
    }

    public String getFcstate() {
        return fcstate;
    }

    public void setFcstate(String fcstate) {
        this.fcstate = fcstate;
    }

    public String getDefine() {
        return define;
    }

    public void setDefine(String define) {
        this.define = define;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getInsid() {
        return insid;
    }

    public void setInsid(Long insid) {
        this.insid = insid;
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

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public Long getEleid() {
        return eleid;
    }

    public void setEleid(Long eleid) {
        this.eleid = eleid;
    }

    public double getDays() {
        return days;
    }

    public void setDays(double days) {
        this.days = days;
    }

    public double getHorus() {
        return horus;
    }

    public void setHorus(double horus) {
        this.horus = horus;
    }

    public int getIsleak() {
        return isleak;
    }

    public void setIsleak(int isleak) {
        this.isleak = isleak;
    }

    public double getLeakagerate() {
        return leakagerate;
    }

    public void setLeakagerate(double leakagerate) {
        this.leakagerate = leakagerate;
    }

    public double getLeakageratepjxs() {
        return leakageratepjxs;
    }

    public void setLeakageratepjxs(double leakageratepjxs) {
        this.leakageratepjxs = leakageratepjxs;
    }

    public String getDet() {
        return det;
    }

    public void setDet(String det) {
        this.det = det;
    }

    public String getLeak() {
        return leak;
    }

    public void setLeak(String leak) {
        this.leak = leak;
    }

    public String getLeaksource() {
        return leaksource;
    }

    public void setLeaksource(String leaksource) {
        this.leaksource = leaksource;
    }

    public String getRepairmethod() {
        return repairmethod;
    }

    public void setRepairmethod(String repairmethod) {
        this.repairmethod = repairmethod;
    }

    public String getOriginalfcvalue() {
        return originalfcvalue;
    }

    public void setOriginalfcvalue(String originalfcvalue) {
        this.originalfcvalue = originalfcvalue;
    }

    public Long getPvid() {
        return pvid;
    }

    public void setPvid(Long pvid) {
        this.pvid = pvid;
    }

    public String getCheckpeople() {
        return checkpeople;
    }

    public void setCheckpeople(String checkpeople) {
        this.checkpeople = checkpeople;
    }
}
