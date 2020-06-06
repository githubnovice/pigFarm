package com.pig.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.Piglet;
import com.pig.product.service.IPigletService;
import com.pig.product.util.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * 猪仔管理
 */
@RestController
@RequestMapping("/piglet/")
@CrossOrigin
public class PigletController extends BaseController {

    @Autowired
    private IPigletService iPigletService;

    /***
     * 查询猪场猪仔
     * @param page
     * @param limit
     * @param pid
     * @return
     */
    @GetMapping("getPigletByPid")
    public Object getPigletByPid(Integer page,Integer limit, Integer pid,String pnumber){
        Page list = iPigletService.getPigletByPid(page,limit,pid,pnumber);
        return renderSuccess(list.getTotal(),list.getRecords());
    }

    /***
     * 添加猪仔Or修改
     * @param piglet
     * @return
     */
    @PostMapping("addOrModifyPiglet")
    public Object addOrModifyPiglet(Piglet piglet){
        int bool = iPigletService.addOrModifyPiglet(piglet);
        return renderSuccess(bool);
    }

    /***
     * 修改为已出库
     * @param piglet
     * @return
     */
    @PostMapping("modifyPigletOut")
    public Object modifyPigletOut(Piglet piglet){
        int bool = iPigletService.modifyPigletOut(piglet);
        return renderSuccess(bool);
    }

    /***
     * 查询本场未出库猪
     * @param pid
     * @return
     */
    @PostMapping("getInPigByPid")
    public Object getInPigByPid(Integer pid){
        List list = iPigletService.getInPigByPid(pid);
        return renderSuccess(list);
    }

}
