package com.pig.product.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 材料进库
 * </p>
 *
 * @author ST
 * @since 2020-05-25
 */
@Data
public class Material extends Model<Material> {

    private static final long serialVersionUID=1L;

    @TableId(value = "m_id", type = IdType.AUTO)
    private Long mId;

    /**
     * 添加人唯一编号
     */
    private String uNumber;

    /**
     * 材料明细唯一编号
     */
    private String mNumber;

    /**
     * 材料名称
     */
    private String mName;

    /**
     * 材料数量
     */
    private Integer mCount;

    /**
     * 材料单价
     */
    private BigDecimal mPrice;

    /**
     * 材料总价
     */
    private BigDecimal mTotal;

    /**
     * 添加时间
     */
    private Date mAddTime;

    /**
     * 材料使用量
     */
    private Integer mUsage;

    /**
     * 备注
     */
    private String mRemarks;

    /**
     * 猪场ID
     */
    private Integer pId;
}
