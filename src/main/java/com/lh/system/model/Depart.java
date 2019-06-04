package com.lh.system.model;

import lombok.Data;

import java.util.Date;

@Data
public class Depart {
    private String id;

    private String parentId;

    private String name;

    private String shortName;

    private String managerId;

    private String managerName;

    private String telephone;

    private String address;

    private String zipCode;

    private String orgDomain;

    private Integer nodeType;

    private String uniquecoding;

    private Integer adminLevel;

    private Integer state;

    private String createId;

    private String createName;

    private Date createDate;

    private Integer sort;

    private String remark;

}