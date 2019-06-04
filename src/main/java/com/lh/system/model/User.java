package com.lh.system.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String id;

    private String name;

    private String nickName;

    private String loginName;

    private String password;

    private Integer jobs;

    private Integer sex;

    private Date birthday;

    private String picture;

    private String cardId;

    private String mobilePhone;

    private String telephone;

    private String shortTel;

    private String fax;

    private String address;

    private String email;

    private String ipAddress;

    private Date regDate;

    private Integer loginCount;

    private Integer toDayLoginCount;

    private String createId;

    private String createName;

    private Date createDate;

    private Integer sort;

    private Integer state;

    private String remark;

    private String departId;
}