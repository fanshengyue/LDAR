package com.lm.ldar.entity;

import java.io.Serializable;

/**
 * Created by fanshengyue on 2018/4/3.
 */

public class BlueToothMax implements Serializable {
    private double max;
    private Long eleid;
    private String fcvalue;

    public String getFcvalue() {
        return fcvalue;
    }

    public void setFcvalue(String fcvalue) {
        this.fcvalue = fcvalue;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public Long getEleid() {
        return eleid;
    }

    public void setEleid(Long eleid) {
        this.eleid = eleid;
    }


}
