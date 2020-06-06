package com.pig.product.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 日常支出
 * </p>
 *
 * @author ST
 * @since 2020-05-25
 */
@Data
public class Expend extends Model<Expend> {

    private static final long serialVersionUID=1L;

    @TableId(value = "e_id", type = IdType.AUTO)
    private Long eId;

    /**
     * 用途
     */
    private String ePurpose;

    /**
     * 支出金额
     */
    private BigDecimal eMoney;

    /**
     * 支出时间(年月日)
     */
    private Date eTime;

    /**
     * 付钱的人ID
     */
    private Integer sId;

    /**
     * 操作人唯一编号
     */
    private String uNumber;

    /**
     * 猪场ID
     */
    private Integer pId;
}
