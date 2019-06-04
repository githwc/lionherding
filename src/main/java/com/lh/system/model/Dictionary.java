package com.lh.system.model;

import lombok.Data;

import java.util.Date;

@Data
public class Dictionary {
    private String id;

    private Date createDate;

    private String createId;

    private String createName;

    private String key;

    private String parentId;

    private Integer sort;

    private String value;
}