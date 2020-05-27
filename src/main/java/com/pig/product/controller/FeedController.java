package com.pig.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.Feed;
import com.pig.product.entity.Feeduse;
import com.pig.product.service.IFeedService;
import com.pig.product.service.IFeeduseService;
import com.pig.product.util.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 饲料管理
 */
@RestController
@RequestMapping("/feed/")
@CrossOrigin
public class FeedController extends BaseController {

    @Autowired
    private IFeedService iFeedService;
    @Autowired
    private IFeeduseService iFeeduseService;

    /***
     * 饲料入库Or修改
     * @param feed
     * @return
     */
    @PostMapping("insertOrModifyFeed")
    public Object insertOrModifyFeed(Feed feed){
        int bool = iFeedService.insertOrModifyFeed(feed);
        return bool == 1 ? renderSuccess("成功") : renderError("失败");
    }

    /***
     * 查询本场入库饲料
     * @param curren 页数
     * @param size   每页几条
     * @param pid    猪场ID
     * @param fName  饲料名称
     * @return
     */
    @GetMapping("getFeedByList")
    public Object getFeedByList(Integer curren,Integer size,Integer pid,String fName){
        Page list = iFeedService.getFeedByList(curren,size,pid,fName);
        return list.getTotal() > 0 ? renderSuccess(list.getRecords()) : renderError("暂无数据");
    }

    /***
     * 查询本场未用完且未退回饲料
     * @param pid  猪场ID
     * @return
     */
    @PostMapping("getFeedByPid")
    public Object getFeedByPid(Integer pid){
        List list = iFeedService.getFeedByPid(pid);
        return list.size() > 0 ? renderSuccess(list) : renderError("暂无饲料");
    }

    /**
     * 添加饲料使用记录 并修改饲料使用量
     * @param feeduse
     * @return
     */
    @PostMapping("useFeedRecord")
    @Transactional
    public Object useFeedRecord(Feeduse feeduse){
        if(null == feeduse.getId()){
            //添加使用记录
            Feed feed = iFeedService.useFeedRecord(feeduse.getFUseFid());
            if(feed.getFSurplusNumber() >= feeduse.getFUserNumber()){
                int bool = iFeeduseService.useFeedRecord(feeduse);
                if(bool == 1){
                    Feed fobj = new Feed();
                    fobj.setFId(feed.getFId());
                    fobj.setFUseTotal(feed.getFUseTotal()+feeduse.getFUserNumber());
                    fobj.setFSurplusNumber(feed.getFSurplusNumber()-feeduse.getFUserNumber());
                    int sta = iFeedService.insertOrModifyFeed(fobj);
                    return sta == 1 ? renderSuccess("记录成功") : renderError("记录失败");
                }else{
                    return renderError("记录失败");
                }
            }
            return renderError("该饲料总数不够本次使用");
        }else{
            //修改使用记录
            Feeduse use = iFeeduseService.getFeeduseById(feeduse.getId());
            if(null != use){
                Integer result = 0;
                int bool;
                if(use.getFUserNumber() < feeduse.getFUserNumber()){
                    //代表是要在原来基础上增加
                    Integer state = 1;
                    //要添加的数量
                    result = feeduse.getFUserNumber() - use.getFUserNumber();
                    bool = iFeedService.modifyFeedById(state,feeduse.getFUseFid(),result);
                }else{
                    Integer state = 0;
                    result = use.getFUserNumber() - feeduse.getFUserNumber();
                    bool = iFeedService.modifyFeedById(state,feeduse.getFUseFid(),result);
                }
                if(1 == bool){
                    int sta = iFeeduseService.modifyFeedUseById(feeduse);
                    return sta == 1 ? renderSuccess("修改成功") : renderError("修改失败");
                }else{
                    return renderSuccess("修改失败");
                }
            }else{
                return renderError("该记录不存在");
            }
        }
    }

    /***
     * 查询饲料饲料带分页
     * @param curren
     * @param size
     * @param pid
     * @param uname
     * @return
     */
    @GetMapping("getUseFeedRecord")
    public Object getUseFeedRecord(Integer curren,Integer size,Integer pid,String uname){
        IPage page = iFeeduseService.getUseFeedRecord(curren,size,pid,uname);
        return page.getTotal() > 0 ? renderSuccess(page.getRecords()) : renderError("暂无数据");
    }
}
