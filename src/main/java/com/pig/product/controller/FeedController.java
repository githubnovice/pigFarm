package com.pig.product.controller;

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
     * @param page 页数
     * @param limit   每页几条
     * @param pid    猪场ID
     * @param fName  饲料名称
     * @return
     */
    @GetMapping("getFeedByList")
    public Object getFeedByList(Integer page,Integer limit,Integer pid,String fName){
        Page list = iFeedService.getFeedByList(page,limit,pid,fName);
        return list.getTotal() > 0 ? renderSuccess(list.getTotal(),list.getRecords()) : renderError("暂无数据");
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
    public Object useFeedRecord(Feeduse feeduse) {
        Long id = feeduse.getId();
        if (null == id) {
            //添加使用记录
            Feed feed = iFeedService.useFeedRecord(feeduse.getFUseFid());
            Integer surplusNumber = feed.getFSurplusNumber();
            Integer userNumber = feeduse.getFUserNumber();
            if (surplusNumber < userNumber)
                return renderError("该饲料总数不够本次使用");
            int bool = iFeeduseService.useFeedRecord(feeduse);
            if (bool != 1)
                return renderError("记录失败");
            Feed fobj = new Feed();
            fobj.setFId(feed.getFId());
            fobj.setFUseTotal(feed.getFUseTotal() + userNumber);
            fobj.setFSurplusNumber(surplusNumber - userNumber);
            int sta = iFeedService.insertOrModifyFeed(fobj);
            return sta == 1 ? renderSuccess("记录成功") : renderError("记录失败");
        } else {
            //修改使用记录
            Feeduse use = iFeeduseService.getFeeduseById(id);
            if (null == use)
                return renderError("该记录不存在");
            //使用过的饲料数量
            Integer userNumber = use.getFUserNumber();
            //要修改的使用饲料总数
            Integer fuserNumber = feeduse.getFUserNumber();
            boolean flag = userNumber <= fuserNumber;
            Integer state = flag ? 1 : 0;
            Integer result = flag ? fuserNumber - userNumber : userNumber - fuserNumber;
            int bool = iFeedService.modifyFeedById(state, feeduse.getFUseFid(), result);
            if (1 != bool)
                return renderSuccess("修改失败");
            int sta = iFeeduseService.modifyFeedUseById(feeduse);
            return sta == 1 ? renderSuccess("修改成功") : renderError("修改失败");
        }
    }

    /***
     * 查询饲料饲料带分页
     * @param page
     * @param limit
     * @param pid
     * @param uname
     * @return
     */
    @GetMapping("getUseFeedRecord")
    public Object getUseFeedRecord(Integer page,Integer limit,Integer pid,String uname){
        Page pages = iFeeduseService.getUseFeedRecord(page,limit,pid,uname);
        return pages.getTotal() > 0 ? renderSuccess(pages.getTotal(),pages.getRecords()) : renderError("暂无数据");
    }
}
