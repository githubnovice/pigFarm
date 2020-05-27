package com.pig.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.Feed;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 饲料进库 服务类
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
public interface IFeedService extends IService<Feed> {

    int insertOrModifyFeed(Feed feed);

    Page getFeedByList(Integer curren, Integer size, Integer pid, String fName);

    List getFeedByPid(Integer pid);

    Feed useFeedRecord(Long fUseFid);

    int modifyFeedById(Integer state,Long fUseFid, Integer result);
}
