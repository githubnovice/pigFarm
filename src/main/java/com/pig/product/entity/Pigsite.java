package com.pig.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 猪场管理
 * </p>
 *
 * @author ST
 * @since 2020-05-25
 */
@Data
public class Pigsite extends Model<Pigsite> {

    private static final long serialVersionUID=1L;

    @TableId(value = "p_id", type = IdType.AUTO)
    private Integer pId;

    /**
     * 猪场名字
     */
    private String pName;

    /**
     * 猪场地址
     */
    private String pAddress;

    /**
     * 猪场规模
     */
    private Integer pScale;
}
