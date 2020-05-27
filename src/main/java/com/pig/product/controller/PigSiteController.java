package com.pig.product.controller;

import com.pig.product.entity.Pigsite;
import com.pig.product.service.IPigsiteService;
import com.pig.product.util.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * 猪场管理
 */
@RestController
@RequestMapping("/pigsite/")
@CrossOrigin
public class PigSiteController extends BaseController {

    @Autowired
    private IPigsiteService iPigsiteService;

    /***
     * 添加修改猪场
     * @return
     */
    @PostMapping("addOrModifyPigsite")
    private Object addOrModifyPigsite(Pigsite pigsite){
        int bool = iPigsiteService.addOrModifyPigsite(pigsite);
        return bool == 1 ? renderSuccess("成功") : renderError("失败");
    }

    /***
     * 查询所有猪场
     * @return
     */
    @GetMapping("getPigsiteList")
    public Object getPigsiteList(String pname){
        List list = iPigsiteService.getPigsiteList(pname);
        return list.size() > 0 ? renderSuccess(list) : renderError("暂无数据");
    }
}
