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
 * 材料使用日志
 * </p>
 *
 * @author ST
 * @since 2020-05-25
 */
@Data
public class Usemateriallog extends Model<Usemateriallog> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 材料明细唯一编号
     */
    private String mNumber;

    /**
     * 使用时间(年月日)
     */
    private Date mUseTime;

    /**
     * 用途
     */
    private String mPurpose;

    /**
     * 操作人唯一编号
     */
    private String uNumber;

    /**
     * 备注
     */
    private String mRemarks;

    /**
     * 使用数量
     */
    private Integer mUsage;

    /**
     * 使用人
     */
    private Integer mSid;

    /**
     * 猪场ID
     */
    private Integer pId;
}
