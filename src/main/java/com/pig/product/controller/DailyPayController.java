package com.pig.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.Expend;
import com.pig.product.service.IExpendService;
import com.pig.product.util.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 日常支出
 */
@RestController
@RequestMapping("/dailyPay/")
@CrossOrigin
public class DailyPayController extends BaseController {

    @Autowired
    private IExpendService iExpendService;

    /***
     * 添加Or修改日常支出
     * @param expend
     * @return
     */
    @PostMapping("addOrModifyExpend")
    public Object addOrModifyExpend(Expend expend){
        int bool = iExpendService.addOrModifyExpend(expend);
        return 1 == bool ? renderSuccess("操作成功") : renderError("操作失败");
    }

    /***
     * 查询本场日常支出
     * @param page
     * @param limit
     * @param pid
     * @param ePurpose
     * @return
     */
    @GetMapping("getExpendByList")
    public Object getExpendByList(Integer page,Integer limit,Integer pid,String ePurpose){
        Page pages = iExpendService.getExpendByList(page,limit,pid,ePurpose);
        return renderSuccess(pages.getTotal(),pages.getRecords());
    }

    /***
     * 统计本周支出
     * @return
     */
    @PostMapping("getExpendPayCount")
    public Object getExpendPayCount(Integer pid){
        String countPay = iExpendService.getExpendPayCount(pid);
        return null != countPay ? renderSuccess(countPay) : renderError("暂无数据");
    }
}
