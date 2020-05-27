package com.pig.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig.product.entity.Feed;
import com.pig.product.mapper.FeedMapper;
import com.pig.product.service.IFeedService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 饲料进库 服务实现类
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
@Service
public class FeedServiceImpl extends ServiceImpl<FeedMapper, Feed> implements IFeedService {

    @Resource
    private FeedMapper feedMapper;

    /**
     * 饲料入库修改
     *
     * @param feed
     * @return
     */
    @Override
    public int insertOrModifyFeed(Feed feed) {
        if (null == feed.getFId()) {
            feed.setFTime(new Date());
            feed.setFSurplusNumber(feed.getFTotal());
            int bool = feedMapper.insert(feed);
            return bool;
        } else {
            int bool = feedMapper.updateById(feed);
            return bool;
        }
    }

    /**
     * 本场饲料 带分页
     *
     * @param curren
     * @param size
     * @param pid
     * @param fName
     * @return
     */
    @Override
    public Page getFeedByList(Integer curren, Integer size, Integer pid, String fName) {
        if (null == curren) {
            curren = 1;
        }
        if (null == size) {
            size = 10;
        }
        Page page = new Page(curren, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (0 == pid) {
            if (null != fName) {
                queryWrapper.like("f_name", fName);
            }
            Page mapIPage = feedMapper.selectPage(page, queryWrapper);
            return mapIPage;
        } else {
            queryWrapper.eq("p_id", pid);
            if (null != fName) {
                queryWrapper.like("f_name", fName);
            }
            Page mapIPage = feedMapper.selectPage(page, queryWrapper);
            return mapIPage;
        }
    }

    /***
     * 查询本场未使用完且 未退回饲料
     * @param pid
     * @return
     */
    @Override
    public List getFeedByPid(Integer pid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("p_id", pid);
        queryWrapper.eq("f_return", 0);
        return feedMapper.selectList(queryWrapper);
    }

    /***
     * 根据ID 进库查询饲料
     * @param fUseFid
     * @return
     */
    @Override
    public Feed useFeedRecord(Long fUseFid) {
        return feedMapper.selectById(fUseFid);
    }

    /***
     * 根据ID 修改 使用记录修改时饲料的增减
     * @param fUseFid
     * @param result
     * @return
     */
    @Override
    public int modifyFeedById(Integer state, Long fUseFid, Integer result) {
        Feed feed = feedMapper.selectById(fUseFid);
        if (1 != state) {
            feed.setFUseTotal(feed.getFUseTotal() - result);
            feed.setFSurplusNumber(feed.getFSurplusNumber() + result);
            return feedMapper.updateById(feed);
        }
        if (feed.getFSurplusNumber() - result <= 0)
            return 0;
        feed.setFUseTotal(feed.getFUseTotal() + result);
        feed.setFSurplusNumber(feed.getFSurplusNumber() - result);
        return feedMapper.updateById(feed);
    }
}
