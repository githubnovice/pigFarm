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
 * 饲料使用
 * </p>
 *
 * @author ST
 * @since 2020-05-25
 */
@Data
public class Feeduse extends Model<Feeduse> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 使用饲料ID
     */
    private Long fUseFid;

    /**
     * 使用数量
     */
    private Integer fUserNumber;

    /**
     * 使用时间
     */
    private Date fUseTime;

    /**
     * 使用人
     */
    private Integer fSid;

    /**
     * 操作人编号
     */
    private String uNumber;

    /**
     * 用于几号圈
     */
    private Integer fPigsty;

    /**
     * 猪场ID
     */
    private Integer pId;
}
