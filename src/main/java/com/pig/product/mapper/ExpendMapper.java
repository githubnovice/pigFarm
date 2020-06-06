package com.pig.product.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.Expend;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 日常支出 Mapper 接口
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
public interface ExpendMapper extends BaseMapper<Expend> {

    List getExpendByList(Page page, @Param("pid") Integer pid, @Param("ePurpose") String ePurpose);

    String getExpendPayCount(@Param("pid") Integer pid, @Param("toLocaleString") String toLocaleString, @Param("toLocaleString1") String toLocaleString1);
}
