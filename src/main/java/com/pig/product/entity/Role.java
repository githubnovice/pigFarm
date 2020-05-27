package com.pig.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 角色管理
 * </p>
 *
 * @author ST
 * @since 2020-05-25
 */
@Data
public class Role extends Model<Role> {

    private static final long serialVersionUID=1L;

    @TableId(value = "r_id", type = IdType.AUTO)
    private Integer rId;

    /**
     * 角色名
     */
    private String rName;

    /**
     * 添加时间
     */
    private LocalDateTime rTime;

    /**
     * 添加人唯一编号
     */
    private Integer uNumber;

    /**
     * 菜单ID
     */
    private Integer mId;

    /**
     * 猪场ID
     */
    private Integer pId;
}
