package com.lm.ldar.entity;

import java.io.Serializable;

/**
 * 命名规则表
 * Created by xieweikun on 2018/1/14.
 */

public class Namerules implements Serializable {
    private Long id;
    private int eid;
    private String name;//命名规则名称
    private String famen;//阀门
    private String beng;//泵
    private String falan;//法兰
    private String yasuoji;//压缩机
    private String jiaoban;//搅拌器
    private String xieya;//泄压装置
    private String lianjie;//连接件
    private String kaikou;//开口管线或开口阀
    private String caiyang;//采样连接系统
    private String other;//其它

    public Namerules() {
    }

    public Namerules(Long id, int eid, String name, String famen, String beng, String falan, String yasuoji, String jiaoban, String xieya, String lianjie, String kaikou, String caiyang, String other) {
        this.id = id;
        this.eid = eid;
        this.name = name;
        this.famen = famen;
        this.beng = beng;
        this.falan = falan;
        this.yasuoji = yasuoji;
        this.jiaoban = jiaoban;
        this.xieya = xieya;
        this.lianjie = lianjie;
        this.kaikou = kaikou;
        this.caiyang = caiyang;
        this.other = other;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamen() {
        return famen;
    }

    public void setFamen(String famen) {
        this.famen = famen;
    }

    public String getBeng() {
        return beng;
    }

    public void setBeng(String beng) {
        this.beng = beng;
    }

    public String getFalan() {
        return falan;
    }

    public void setFalan(String falan) {
        this.falan = falan;
    }

    public String getYasuoji() {
        return yasuoji;
    }

    public void setYasuoji(String yasuoji) {
        this.yasuoji = yasuoji;
    }

    public String getJiaoban() {
        return jiaoban;
    }

    public void setJiaoban(String jiaoban) {
        this.jiaoban = jiaoban;
    }

    public String getXieya() {
        return xieya;
    }

    public void setXieya(String xieya) {
        this.xieya = xieya;
    }

    public String getLianjie() {
        return lianjie;
    }

    public void setLianjie(String lianjie) {
        this.lianjie = lianjie;
    }

    public String getKaikou() {
        return kaikou;
    }

    public void setKaikou(String kaikou) {
        this.kaikou = kaikou;
    }

    public String getCaiyang() {
        return caiyang;
    }

    public void setCaiyang(String caiyang) {
        this.caiyang = caiyang;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
