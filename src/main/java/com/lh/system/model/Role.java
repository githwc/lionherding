package com.lh.system.model;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private String id;

    private Date createDate;

    private String createId;

    private String createName;

    private String name;

    private String remark;

    private Integer sort;

    private Integer state;
}