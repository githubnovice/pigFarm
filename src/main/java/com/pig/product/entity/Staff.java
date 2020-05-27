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
 * 员工管理
 * </p>
 *
 * @author ST
 * @since 2020-05-25
 */
@Data
public class Staff extends Model<Staff> {

    private static final long serialVersionUID=1L;

    @TableId(value = "s_id", type = IdType.AUTO)
    private Long sId;

    /**
     * 员工姓名
     */
    private String sName;

    /**
     * 员工年龄
     */
    private Integer sAge;

    /**
     * 员工身份证号
     */
    private String sIdNumber;

    /**
     * 入职时间
     */
    private Date sEntryTime;

    /**
     * 是否离职（1未离职，0离职）
     */
    private Integer sState;

    /**
     * 是否隐藏年龄（0隐藏，1不隐藏）
     */
    private Integer sHideAge;

    /**
     * 是否隐藏身份证(1不隐藏，0隐藏)
     */
    private Integer sHideIdNumber;

    /**
     * 猪场ID
     */
    private Integer pId;
}
