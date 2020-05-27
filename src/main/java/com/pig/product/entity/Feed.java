package com.pig.product.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 饲料进库
 * </p>
 *
 * @author ST
 * @since 2020-05-25
 */
@Data
public class Feed extends Model<Feed> {

    private static final long serialVersionUID=1L;

    @TableId(value = "f_id", type = IdType.AUTO)
    private Long fId;

    /**
     * 饲料名称
     */
    private String fName;

    /**
     * 饲料运费
     */
    private BigDecimal fFreight;

    /**
     * 饲料总数
     */
    private Integer fTotal;

    /**
     * 已使用数量
     */
    private Integer fUseTotal;

    /**
     * 剩余数量
     */
    private Integer fSurplusNumber;

    /**
     * 添加时间
     */
    private Date fTime;

    /**
     * 操作人
     */
    private String uNumber;

    /**
     * 是否退回（0未使用完，1使用完了，2已退回）
     */
    private Integer fReturn;

    /**
     * 猪场ID
     */
    private Integer pId;
}
