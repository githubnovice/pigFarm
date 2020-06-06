package com.pig.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 猪仔进库
 * </p>
 *
 * @author ST
 * @since 2020-05-25
 */
@Data
public class Piglet extends Model<Piglet> {

    private static final long serialVersionUID=1L;

    @TableId(value = "p_id", type = IdType.AUTO)
    private Long pId;

    /**
     * 进库编号
     */
    private String pNumber;

    /**
     * 猪仔数量
     */
    private Integer pTotal;

    /**
     * 死亡数量
     */
    private Integer pDeathTotal;

    /**
     * 异常数量
     */
    private Integer pProblemTotal;

    /**
     * 进库时间
     */
    private Date pInstorageTime;

    /**
     * 出栏时间
     */
    private Date pOutboundTime;

    /**
     * 是否出库（0未出库，1出库）
     */
    private Integer pState;

    /**
     * 猪场ID
     */
    private Integer pIds;
}
