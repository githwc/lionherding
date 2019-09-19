package com.lh.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述：
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author  xieyc
 * @Date 2019-07-04
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String SESSION_CURRENT_USER = "SYSUSER";

    private String id;

    @JsonView(userSimpleView.class)
    private String name;

    @JsonView(userSimpleView.class)
    private String nickName;

    @JsonView(userSimpleView.class)
    private String loginName;

    @JsonView(userDetailView.class)
    private String password;

    private Integer age;

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


    public interface userSimpleView{};

    public interface userDetailView extends userSimpleView{};
}
