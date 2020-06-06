package com.pig.product.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.Usemateriallog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 材料使用日志 Mapper 接口
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
public interface UsemateriallogMapper extends BaseMapper<Usemateriallog> {

    List getUseMaterialByList(Page page, @Param("pid") Integer pid, @Param("purpose") String purpose);
}
