package com.lm.ldar.entity;

import java.io.Serializable;

/**
 * 组件类型表
 * Created by xieweikun on 2018/1/14.
 */

public class Ctype implements Serializable {
    private Long id;//组件一级分类ID
    private String description;//描述

    public Ctype() {
    }

    public Ctype(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
