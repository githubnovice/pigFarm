package com.pig.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.Troublepig;
import com.pig.product.mapper.TroublepigMapper;
import com.pig.product.service.ITroublepigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 生猪异常 服务实现类
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
@Service
public class TroublepigServiceImpl extends ServiceImpl<TroublepigMapper, Troublepig> implements ITroublepigService {

    @Resource
    private TroublepigMapper troublepigMapper;

    /***
     * 查询猪仔
     * @param curren
     * @param size
     * @param pid
     * @return
     */
    @Override
    public Page getTroublepigByList(Integer curren, Integer size, Integer pid,String pnumber,Integer state) {
        if(null == curren){
            curren = 1;
        }
        if(null == size){
            size = 10;
        }
        Page page = new Page(curren,size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("p_id",pid);
        queryWrapper.eq("t_state",state);
        if(null != pnumber){
            queryWrapper.like("p_number",pnumber);
        }
        return troublepigMapper.selectPage(page,queryWrapper);
    }

    /***
     * 添加异常猪仔
     * @param troublepig
     * @return
     */
    @Override
    public int addOrModifyTroublepig(Troublepig troublepig,String findtime) throws ParseException {
        if(null == troublepig.getTId()){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            troublepig.setTFindTime(formatter.parse(findtime));
            return troublepigMapper.insert(troublepig);
        }
        return troublepigMapper.updateById(troublepig);
    }

    /***
     * 根据ID 查询异常猪
     * @param tip
     * @return
     */
    @Override
    public Troublepig getTroublepigById(Long tip) {
        return troublepigMapper.selectById(tip);
    }

    /***
     * 从异常猪中修改到死亡
     * @param troublepig
     * @param deathCont
     * @return
     */
    @Override
    @Transactional
    public int addDeathPid(Troublepig troublepig, Integer deathCont) {
        Long tid = troublepig.getTId();
        Troublepig tObj = troublepigMapper.selectById(tid);
        Integer troublePig = tObj.getTTroubleTotal() - deathCont;
        //要修改之前异常猪的数量
        Troublepig tp = new Troublepig();
        tp.setTId(tid);
        tp.setTTroubleTotal(troublePig);
        int bool = troublepigMapper.updateById(tp);
        if(0 == bool){
            return 0;
        }
        //添加死亡猪
        troublepig.setTTroubleTotal(deathCont);
        troublepig.setTFindTime(new Date());
        return troublepigMapper.insert(troublepig);
    }

    /***
     * 添加死亡猪仔
     * @param troublepig
     * @param findtime
     * @return
     */
    @Override
    public int addDeathPig(Troublepig troublepig, String findtime) throws ParseException {
        if(null == troublepig.getTId()){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            troublepig.setTFindTime(formatter.parse(findtime));
            return troublepigMapper.insert(troublepig);
        }
        return troublepigMapper.updateById(troublepig);
    }

}
