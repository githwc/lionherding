package com.lh.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述：Model - 系统用户管理  SYS_User
 * <p>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-05-10 15:20
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String SESSION_CURRENT_USER = "SYSUSER";

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

    @JsonIgnore
    @Transient
    public static User getCurrentUser(HttpServletRequest request) {
        Object object = request.getSession().getAttribute(SESSION_CURRENT_USER);
        return object != null ? (User) object : null;
    }
}