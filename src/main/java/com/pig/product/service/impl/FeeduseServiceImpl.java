package com.pig.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.Feeduse;
import com.pig.product.mapper.FeeduseMapper;
import com.pig.product.service.IFeeduseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 饲料使用 服务实现类
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
@Service
public class FeeduseServiceImpl extends ServiceImpl<FeeduseMapper, Feeduse> implements IFeeduseService {

    @Resource
    private FeeduseMapper feeduseMapper;

    /**
     * 添加饲料使用记录
     * @param feeduse
     * @return
     */
    @Override
    public int useFeedRecord(Feeduse feeduse) {
        feeduse.setFUseTime(new Date());
        return feeduseMapper.insert(feeduse);
    }

    /***
     * 查询本场饲料使用记录 带分页
     * @param curren
     * @param size
     * @param pid
     * @param uname
     * @return
     */
    @Override
    public Page getUseFeedRecord(Integer curren, Integer size, Integer pid, String uname) {
        if(null == curren){
            curren = 1;
        }
        if(null == size){
            size = 10;
        }
        Page page = new Page(curren,size);
        if(null != uname){
            uname = "%"+uname+"%";
        }
        page.setRecords(feeduseMapper.getUseFeedRecord(page,pid,uname));
        return page;
    }

    /***
     * 根据ID 查询饲料
     * @param id
     * @return
     */
    @Override
    public Feeduse getFeeduseById(Long id) {
        return feeduseMapper.selectById(id);
    }

    /***
     * 修改饲料记录
     * @param feeduse
     * @return
     */
    @Override
    public int modifyFeedUseById(Feeduse feeduse) {
        return feeduseMapper.updateById(feeduse);
    }
}
