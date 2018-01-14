package com.lm.ldar.entity;

/**
 * 装置表
 * Created by xieweikun on 2018/1/14.
 */

public class Device {
    private Long id;//装置ID
    private String number;//装置编号
    private String name;//装置名称
    private String createtime;//创建时间
    private int did;//部门ID
    private int eid;//企业ID
    private String productiontime;//投产日期
    private int valid;//状态
    private String safeguard;//检测人员防护措施

    public Device() {
    }

    public Device(Long id, String number, String name, String createtime, int did, int eid, String productiontime, int valid, String safeguard) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.createtime = createtime;
        this.did = did;
        this.eid = eid;
        this.productiontime = productiontime;
        this.valid = valid;
        this.safeguard = safeguard;
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

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getProductiontime() {
        return productiontime;
    }

    public void setProductiontime(String productiontime) {
        this.productiontime = productiontime;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public String getSafeguard() {
        return safeguard;
    }

    public void setSafeguard(String safeguard) {
        this.safeguard = safeguard;
    }
}
