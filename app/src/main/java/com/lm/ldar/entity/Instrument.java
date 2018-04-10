package com.lm.ldar.entity;

import java.io.Serializable;

/**
 * Created by fanshengyue on 2018/3/22.
 * 检测仪器表
 */

public class Instrument implements Serializable {
    private Long id;
    private String insname;//仪器名称
    private Long eid;//企业id

    public Instrument(){}
    public Instrument(Long id,String insname,Long eid){
        this.id=id;
        this.insname=insname;
        this.eid=eid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInsname() {
        return insname;
    }

    public void setInsname(String insname) {
        this.insname = insname;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }
}
