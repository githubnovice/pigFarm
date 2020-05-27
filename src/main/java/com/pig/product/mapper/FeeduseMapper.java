package com.pig.product.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.Feeduse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 饲料使用 Mapper 接口
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
public interface FeeduseMapper extends BaseMapper<Feeduse> {
    //List getUseFeedRecord(Page page, Integer pid, String uname);

    List getUseFeedRecord(Page page, @Param("pid")Integer pid, @Param("uname")String uname);
}
