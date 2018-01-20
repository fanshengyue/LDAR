package com.lm.ldar.entity;

import java.io.Serializable;

/**
 * Created by fanshengyue on 2018/1/20.
 * 临时存储建档图片信息
 */

public class ImageInfoEntity implements Serializable{
    private String location;//位置
    private String direction;//方向
    private String equip;//设备信息
    private String material;//物质信息
    private String pid;//PID图号
    private String distance;//距离
    private String floor;//楼层
    private String height;//高度

    public ImageInfoEntity(){}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getEquip() {
        return equip;
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
