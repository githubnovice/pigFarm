package com.pig.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author ST
 * @since 2020-05-06
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
     * 上级领导唯一编号
     */
    private String uSuperiorLeaderNumber;

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
     * 状态 (1可用，0禁用) 
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
    private Integer uBelongToPigFarm;

    /**
     * 登录权限
     */
    private Integer uPower;

    /**
     * 登录权限
     */
    private Integer uOperatePower;

    /**
     * token
     */
    private String uToken;
}
