package com.pig.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 生猪异常
 * </p>
 *
 * @author ST
 * @since 2020-05-25
 */
@Data
public class Troublepig extends Model<Troublepig> {

    private static final long serialVersionUID=1L;

    @TableId(value = "t_id", type = IdType.AUTO)
    private Long tId;

    /**
     * 猪仔进库编号
     */
    private String pNumber;

    /**
     * 发现时间
     */
    private LocalDateTime tFindTime;

    /**
     * 异常数量
     */
    private String tTroubleTotal;

    /**
     * 如何处理
     */
    private String tHandle;

    /**
     * 是否死亡（0为死亡，1死亡）
     */
    private Integer tState;

    /**
     * 死亡原因
     */
    private String tReason;

    /**
     * 操作人唯一编号
     */
    private String uNumber;

    /**
     * 发现于那个猪圈
     */
    private Integer tPigstyNo;

    /**
     * 猪场ID
     */
    private Integer pId;
}
