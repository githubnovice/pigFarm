package com.pig.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 材料参考
 * </p>
 *
 * @author ST
 * @since 2020-05-25
 */
@Data
public class Reference extends Model<Reference> {

    private static final long serialVersionUID=1L;

    @TableId(value = "r_id", type = IdType.AUTO)
    private Long rId;

    /**
     * 上传人
     */
    private String uNumber;

    /**
     * 材料名字
     */
    private String rName;

    /**
     * 联系电话
     */
    private String rPhone;

    /**
     * 来源（0未知,1淘宝，2京东,3实体店）
     */
    private Integer rSource;

    /**
     * 备注
     */
    private String rRemarks;

    /**
     * 审核状态（0未审核，1审核通过）
     */
    private Integer rState;
}
