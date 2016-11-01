package com.sample.entity;

import com.sgaop.basis.annotation.*;

import java.sql.Timestamp;

/**
 * Created by 30695 on 2016/9/24 0024.
 */
@Table("Topic")
public class Topic {

    @Pk
    @Colum
    private int id;

    //可以直接显示的内容
    @Colum
    private String content;

    //md源码
    @Colum
    private String markdown;

    //是否markdown
    @Colum
    private boolean md;

    @Colum
    private int createUser;

    @Colum
    private Timestamp createTime;

    @Colum
    private Timestamp modifyTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    public boolean isMd() {
        return md;
    }

    public void setMd(boolean md) {
        this.md = md;
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }
}
