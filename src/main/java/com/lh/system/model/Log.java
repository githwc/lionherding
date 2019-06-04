package com.lh.system.model;

import lombok.Data;

import java.util.Date;

@Data
public class Log {
    private String id;

    private String desc;

    private String functionId;

    private String ipAdress;

    private String loginName;

    private Date opDate;

    private Integer opType;

    private String userId;

    private String userName;
}