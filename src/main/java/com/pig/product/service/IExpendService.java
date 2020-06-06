package com.pig.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.Expend;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 * 日常支出 服务类
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
public interface IExpendService extends IService<Expend> {

    int addOrModifyExpend(Expend expend);

    Page getExpendByList(Integer curren, Integer size, Integer pid, String ePurpose);

    String getExpendPayCount(Integer pid);
}
