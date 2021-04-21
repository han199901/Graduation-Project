package com.han.gp.domain;

import java.io.Serializable;
import java.util.Date;

public class TextContent implements Serializable {
    private Integer id;

    private Date createTime;

    private String content;

    public TextContent(){

    }

    public TextContent(String content, Date createTime) {
        this.content = content;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}