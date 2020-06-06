package com.pig.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.Feeduse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 饲料使用 服务类
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
public interface IFeeduseService extends IService<Feeduse> {

    int useFeedRecord(Feeduse feeduse);

    Page getUseFeedRecord(Integer curren, Integer size, Integer pid, String uname);

    Feeduse getFeeduseById(Long id);

    int modifyFeedUseById(Feeduse feeduse);
}
