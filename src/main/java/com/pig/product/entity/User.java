package com.pig.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author ST
 * @since 2020-05-25
 */
@Data
public class User extends Model<User> {

    private static final long serialVersionUID=1L;

    @TableId(value = "u_id", type = IdType.AUTO)
    private Long uId;

    /**
     * 唯一编号
     */
    private String uNumber;

    /**
     * 上级领导编号
     */
    private String uSuperiorLeaderNumber;

    /**
     * 真实姓名
     */
    private String uName;

    /**
     * 账户
     */
    private String uAccount;

    /**
     * 密码
     */
    private String uPassword;

    /**
     * 级别
     */
    private Integer uLevel;

    /**
     * 状态 (2未激活，1可用，0禁用) 
     */
    private Integer uStatus;

    /**
     * 创建时间
     */
    private Date uCreateTime;

    /**
     * 上次登录时间
     */
    private Date uLastLoginTime;

    /**
     * 登录次数
     */
    private Long uLoginCount;

    /**
     * 属于哪个猪场
     */
    private Integer pId;

    /**
     * 登录权限
     */
    private Integer uPower;

    private String uToken;
}
