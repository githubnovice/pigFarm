package com.pig.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 菜单管理
 * </p>
 *
 * @author ST
 * @since 2020-05-25
 */
@Data
public class Menu extends Model<Menu> {

    private static final long serialVersionUID=1L;

    @TableId(value = "m_id", type = IdType.AUTO)
    private Long mId;

    /**
     * 菜单名
     */
    private String title;

    /**
     * 菜单上级ID
     */
    private Integer superiorId;

    /**
     * 菜单路径
     */
    private String href;

    /**
     * 菜单图标
     */
    private String icon;
}
