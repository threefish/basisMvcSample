package com.sample.entity;

import com.sgaop.basis.annotation.Colum;
import com.sgaop.basis.annotation.Pk;
import com.sgaop.basis.annotation.Table;

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
}
