package com.lm.ldar.entity;

import java.io.Serializable;

/**
 * Created by fanshengyue on 2018/3/19.
 */
public class Repair implements Serializable {
    private Long id;//ID
    private String tag;//组件编号
    private String pbvalue;//背景检测值
    private String firstname;//第一次修复人员姓名
    private String firsttime;//第一次修复日期
    private String firstmethod;//第一次修复方式
    private String firstreplay;//第一次修复后浓度，ppmv
    private String firstdropnumber;//第一次修复后每分钟液滴数
    private String secname;//第二次修复人员姓名
    private String sectime;//第二次修复日期
    private String secmethod;//第二次修复方式
    private String secreplay;//第二次修复后浓度，ppmv
    private String secdropnumber;//第二次修复后每分钟液滴数
    private String fcvalue;//泄漏定义，ppmv
    private String define;//泄漏定义值
    private int issuccess;//是否修复成功 1是    0否
    private String delayreason;//延迟原因
    private int isdelay;//是否延期修复 1是    0否
    private Long uid;//
    private String checktime;//
    private Long did;//装置id
    private Long aid;//子区域id
    private Long tid;//任务taskuserid
    private Long pid;//图片id
    private Long wid;//计划ID
    private Long eleid;//标点ID
    private double leakagerate;//泄漏量ppm
    private double repairleakagerate;//修复后泄漏量ppm
    private Long pvid;//图像版本ID
    private String firstcheckpeople;//第一次复测人员
    private String seccheckpeople;//第二次复测人员
    public Repair(){}
    public Repair(Long id,String tag,String pbvalue,String firstname,String firsttime,String firstmethod,String firstreplay,String firstdropnumber,
                  String secname,String sectime,String secmethod,String secreplay,String secdropnumber,String fcvalue,String define,
                  int issuccess,String delayreason,int isdelay,Long uid,String checktime,Long did,Long aid,Long tid,Long pid,Long wid,
                  Long eleid,double leakagerate,double repairleakagerate,Long pvid,String firstcheckpeople,String seccheckpeople){
        this.id=id;
        this.tag=tag;
        this.pbvalue=pbvalue;
        this.firstname=firstname;
        this.firsttime=firsttime;
        this.firstmethod=firstmethod;
        this.firstreplay=firstreplay;
        this.firstdropnumber=firstdropnumber;
        this.secname=secname;
        this.sectime=sectime;
        this.secmethod=secmethod;
        this.secreplay=secreplay;
        this.secdropnumber=secdropnumber;
        this.fcvalue=fcvalue;
        this.define=define;
        this.issuccess=issuccess;
        this.delayreason=delayreason;
        this.isdelay=isdelay;
        this.uid=uid;
        this.checktime=checktime;
        this.did=did;
        this.aid=aid;
        this.tid=tid;
        this.pid=pid;
        this.wid=wid;
        this.eleid=eleid;
        this.leakagerate=leakagerate;
        this.repairleakagerate=repairleakagerate;
        this.pvid=pvid;
        this.firstcheckpeople=firstcheckpeople;
        this.seccheckpeople=seccheckpeople;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirsttime() {
        return firsttime;
    }

    public void setFirsttime(String firsttime) {
        this.firsttime = firsttime;
    }

    public String getFirstmethod() {
        return firstmethod;
    }

    public void setFirstmethod(String firstmethod) {
        this.firstmethod = firstmethod;
    }

    public String getFirstreplay() {
        return firstreplay;
    }

    public void setFirstreplay(String firstreplay) {
        this.firstreplay = firstreplay;
    }

    public String getFirstdropnumber() {
        return firstdropnumber;
    }

    public void setFirstdropnumber(String firstdropnumber) {
        this.firstdropnumber = firstdropnumber;
    }

    public String getSecname() {
        return secname;
    }

    public void setSecname(String secname) {
        this.secname = secname;
    }

    public String getSectime() {
        return sectime;
    }

    public void setSectime(String sectime) {
        this.sectime = sectime;
    }

    public String getSecmethod() {
        return secmethod;
    }

    public void setSecmethod(String secmethod) {
        this.secmethod = secmethod;
    }

    public String getSecreplay() {
        return secreplay;
    }

    public void setSecreplay(String secreplay) {
        this.secreplay = secreplay;
    }

    public String getSecdropnumber() {
        return secdropnumber;
    }

    public void setSecdropnumber(String secdropnumber) {
        this.secdropnumber = secdropnumber;
    }

    public String getFcvalue() {
        return fcvalue;
    }

    public void setFcvalue(String fcvalue) {
        this.fcvalue = fcvalue;
    }

    public String getDefine() {
        return define;
    }

    public void setDefine(String define) {
        this.define = define;
    }

    public int getIssuccess() {
        return issuccess;
    }

    public void setIssuccess(int issuccess) {
        this.issuccess = issuccess;
    }

    public String getDelayreason() {
        return delayreason;
    }

    public void setDelayreason(String delayreason) {
        this.delayreason = delayreason;
    }

    public int getIsdelay() {
        return isdelay;
    }

    public void setIsdelay(int isdelay) {
        this.isdelay = isdelay;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getChecktime() {
        return checktime;
    }

    public void setChecktime(String checktime) {
        this.checktime = checktime;
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

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
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

    public double getLeakagerate() {
        return leakagerate;
    }

    public void setLeakagerate(double leakagerate) {
        this.leakagerate = leakagerate;
    }

    public double getRepairleakagerate() {
        return repairleakagerate;
    }

    public void setRepairleakagerate(double repairleakagerate) {
        this.repairleakagerate = repairleakagerate;
    }

    public Long getPvid() {
        return pvid;
    }

    public void setPvid(Long pvid) {
        this.pvid = pvid;
    }

    public String getFirstcheckpeople() {
        return firstcheckpeople;
    }

    public void setFirstcheckpeople(String firstcheckpeople) {
        this.firstcheckpeople = firstcheckpeople;
    }

    public String getSeccheckpeople() {
        return seccheckpeople;
    }

    public void setSeccheckpeople(String seccheckpeople) {
        this.seccheckpeople = seccheckpeople;
    }
}
