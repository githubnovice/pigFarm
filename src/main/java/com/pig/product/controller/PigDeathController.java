package com.pig.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.Piglet;
import com.pig.product.entity.Troublepig;
import com.pig.product.service.IPigletService;
import com.pig.product.service.ITroublepigService;
import com.pig.product.util.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/***
 * 猪仔异常
 */
@RestController
@RequestMapping("/pigDeath/")
@CrossOrigin
public class PigDeathController extends BaseController {

    @Autowired
    private ITroublepigService iTroublepigService;

    @Autowired
    private IPigletService iPigletService;


    /***
     * 查询异常猪仔
     * @param page
     * @param limit
     * @param pid
     * @return
     */
    @GetMapping("getTroublepigByList")
    public Object getTroublepigByList(Integer page,Integer limit, Integer pid,String pnumber,Integer state){
        Page pages = iTroublepigService.getTroublepigByList(page,limit,pid,pnumber,state);
        return renderSuccess(pages.getTotal(),pages.getRecords());
    }

    /***
     * 添加异常猪仔
     * @param troublepig
     * @return
     */
    @PostMapping("addOrModifyTroublepig")
    @Transactional
    public Object addOrModifyTroublepig(Troublepig troublepig,String findtime) throws ParseException {
        Piglet piglet = iPigletService.getPigletByPNumber(troublepig.getPNumber());
        if(null == piglet){
            return renderError("没有该批次猪");
        }
        Long tId = troublepig.getTId();
        if(null == tId){
            //异常猪
            Integer problemTotal;
            if(null == piglet.getPProblemTotal()){
                problemTotal = 0;
            }else{
                problemTotal = piglet.getPProblemTotal();
            }
            Integer deathTotal ;
            if(null == piglet.getPDeathTotal()){
                deathTotal = 0;
            }else{
                deathTotal = piglet.getPDeathTotal();
            }
            boolean flag = piglet.getPTotal() >= (deathTotal + problemTotal + troublepig.getTTroubleTotal());
            if(false == flag){
                return renderError("异常数量大于正常数量");
            }
            Long pId = piglet.getPId();
            Integer troubleTotal = troublepig.getTTroubleTotal();
            Piglet p = new Piglet();
            p.setPId(pId);
            p.setPProblemTotal(problemTotal + troubleTotal);
            int bool = iPigletService.addOrModifyPiglet(p);
            if(0 == bool){
                return renderError("记录失败");
            }
            int sta = iTroublepigService.addOrModifyTroublepig(troublepig,findtime);
            return renderSuccess(sta);
        }else{
            Long tip = troublepig.getTId();
            Troublepig tl = iTroublepigService.getTroublepigById(tip);
            if(null == tl){
                return renderError("该记录不存在");
            }
            //已存在异常猪数量
            Integer trouble = tl.getTTroubleTotal();
            //要修改的异常猪数量
            Integer troublepigs = troublepig.getTTroubleTotal();
            boolean flag = trouble <= troublepigs;
            Integer state = flag ? 1 : 0;
            Integer result = flag ? troublepigs - trouble : trouble - troublepigs;
            int bool = iPigletService.modifyPigletById(piglet.getPId(),state,result);
            if(bool == 0){
                return renderError("修改失败");
            }
            int bo = iTroublepigService.addOrModifyTroublepig(troublepig,findtime);
            return renderSuccess(bo);
        }
    }

    /***
     * 将异常猪添加到死亡记录中
     * @param troublepig
     * @param deathCont
     * @return
     */
    @PostMapping("addDeathPid")
    @Transactional
    public Object addDeathPid(Troublepig troublepig,Integer deathCont){
        Piglet piglet = iPigletService.getPigletByPNumber(troublepig.getPNumber());
        if(null == piglet){
            return renderError("没有该批次猪");
        }
        int bool = iTroublepigService.addDeathPid(troublepig,deathCont);
        if(bool == 0){
            return renderError("设置失败");
        }
        Piglet pobj = new Piglet();
        pobj.setPId(piglet.getPId());
        pobj.setPDeathTotal(deathCont);
        Integer problemTotal;
        if(null == piglet.getPProblemTotal()){
            problemTotal = 0;
        }else{
            problemTotal = piglet.getPProblemTotal();
        }
        pobj.setPProblemTotal(problemTotal - deathCont);
        int bo = iPigletService.addOrModifyPiglet(pobj);
        return renderSuccess(bo);
    }

    /***
     * 添加死亡猪仔
     * @param troublepig
     * @param findtime
     * @return
     */
    @PostMapping("addDeathPig")
    @Transactional
    public Object addDeathPig(Troublepig troublepig,String findtime) throws ParseException {
        Piglet piglet = iPigletService.getPigletByPNumber(troublepig.getPNumber());
        if(null == piglet){
            renderError("没有该批次猪");
        }
        //之前死亡数量
        Integer deathtotal;
        //异常数量
        Integer problemtotal;
        //本次死亡数量
        Integer troubleTotal = troublepig.getTTroubleTotal();
        if(null == piglet.getPDeathTotal()){
            deathtotal = 0;
        }else{
            deathtotal = piglet.getPDeathTotal();
        }
        if(null == piglet.getPProblemTotal()){
            problemtotal = 0;
        }else{
            problemtotal = piglet.getPProblemTotal();
        }
        if(piglet.getPTotal() <= (deathtotal + problemtotal + troublepig.getTTroubleTotal())){
            return renderError("该批次猪没有正常的");
        }
        if(null == troublepig.getTId()){
            Piglet p = new Piglet();
            p.setPId(piglet.getPId());
            p.setPDeathTotal(deathtotal + troubleTotal);
            int boo = iPigletService.modifyPiglet(p);
            if(0 == boo){
                return renderError("添加失败");
            }
            int bool = iTroublepigService.addDeathPig(troublepig,findtime);
            return renderSuccess(bool);
        }else{
            Troublepig obj = iTroublepigService.getTroublepigById(troublepig.getTId());
            //之前死亡量
            Integer pigcount = obj.getTTroubleTotal();
            //要修改的死亡量
            Integer deathTotal = troublepig.getTTroubleTotal();
            boolean flag = pigcount <= deathTotal;
            Integer result = flag ? deathTotal - pigcount : pigcount - deathTotal;
            Piglet pig = new Piglet();
            pig.setPId(piglet.getPId());
            if(flag){
                pig.setPDeathTotal(deathtotal + result);
            }
            else{
                pig.setPDeathTotal(deathtotal - result);
            }
            int bool = iPigletService.addOrModifyPiglet(pig);
            if(0 == bool){
                return renderError("修改失败");
            }
            int boo = iTroublepigService.addDeathPig(troublepig,findtime);
            return renderSuccess(boo);
        }
    }
}
