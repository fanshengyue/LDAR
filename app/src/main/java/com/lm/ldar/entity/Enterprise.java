package com.lm.ldar.entity;

import java.io.Serializable;

/**
 * Created by fanshengyue on 2018/1/13.
 */

public class Enterprise implements Serializable {
    /** Not-null value. */
    private Long id;
    private String ecode;//组织机构代码
    private String ename;//企业名称
    private String legal_persion;//企业法人
    private String industry;//行业
    private String create_time;
    private int valid;//用户状态 0--禁用，1--启用
    private int nid;//命名规则id
    private int ppid;//图像参数设置ID
    private int epid;//标点信息参数ID

    public Enterprise(Long id,String ecode,String ename,String legal_persion,String industry,String create_time,
                      int valid,int nid,int ppid,int epid){
        this.id=id;
        this.ecode=ecode;
        this.ename=ename;
        this.legal_persion=legal_persion;
        this.industry=industry;
        this.create_time=create_time;
        this.valid=valid;
        this.nid=nid;
        this.ppid=ppid;
        this.epid=epid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEcode() {
        return ecode;
    }

    public void setEcode(String ecode) {
        this.ecode = ecode;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getLegal_persion() {
        return legal_persion;
    }

    public void setLegal_persion(String legal_persion) {
        this.legal_persion = legal_persion;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
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

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public int getPpid() {
        return ppid;
    }

    public void setPpid(int ppid) {
        this.ppid = ppid;
    }

    public int getEpid() {
        return epid;
    }

    public void setEpid(int epid) {
        this.epid = epid;
    }
}
