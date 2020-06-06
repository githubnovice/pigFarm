package com.pig.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.Piglet;
import com.pig.product.mapper.PigletMapper;
import com.pig.product.service.IPigletService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig.product.util.UniqueNumber;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 猪仔进库 服务实现类
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
@Service
public class PigletServiceImpl extends ServiceImpl<PigletMapper, Piglet> implements IPigletService {

    @Resource
    private PigletMapper pigletMapper;

    /**
     * 查询本场猪仔
     * @param pid
     * @return
     */
    @Override
    public Page getPigletByPid(Integer curren, Integer size, Integer pid,String pnumber) {
        if(null == curren){
            curren = 1;
        }if(null == size){
            size = 10;
        }
        Page page = new Page(curren,size);
        QueryWrapper queryWrapper = new QueryWrapper();
        if(null != pnumber && !"".equals(pnumber)){
            queryWrapper.like("p_number",pnumber);
        }
        queryWrapper.eq("p_ids",pid);
        return pigletMapper.selectPage(page,queryWrapper);
    }

    /***
     * 添加猪仔Or修改
     * @param piglet
     * @return
     */
    @Override
    public int addOrModifyPiglet(Piglet piglet) {
        if(null == piglet.getPId()){
            piglet.setPNumber(UniqueNumber.makeOrderNum());
            piglet.setPInstorageTime(new Date());
            return pigletMapper.insert(piglet);
        }
        if(null != piglet.getPState() && 1 == piglet.getPState()){
            piglet.setPOutboundTime(new Date());
        }
        return pigletMapper.updateById(piglet);
    }

    /***
     * 修改该批猪为出库状态
     * @param piglet
     * @return
     */
    @Override
    public int modifyPigletOut(Piglet piglet) {
        piglet.setPOutboundTime(new Date());
        return pigletMapper.updateById(piglet);
    }

    /***
     * 查询本场未出库猪仔
     * @param pid
     * @return
     */
    @Override
    public List getInPigByPid(Integer pid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("p_state",0);
        queryWrapper.eq("p_ids",pid);
        return pigletMapper.selectList(queryWrapper);
    }

    /***
     * 根据进库编号查询猪仔
     * @param pNumber
     * @return
     */
    @Override
    public Piglet getPigletByPNumber(String pNumber) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("p_number",pNumber);
        return pigletMapper.selectOne(queryWrapper);
    }

    /***
     * 根据ID 修改异常猪
     * @param pId
     * @param state
     * @param result
     * @return
     */
    @Override
    public int modifyPigletById(Long pId, Integer state, Integer result) {
        Piglet piglet = pigletMapper.selectById(pId);
        if(state == 1){
            Integer problem;
            if(null == piglet.getPProblemTotal()){
                problem = result;
            }
            else{
                problem = piglet.getPProblemTotal()+result;
            }
            if(piglet.getPTotal() <= problem){
                return 0;
            }
            Piglet p = new Piglet();
            p.setPId(pId);
            p.setPProblemTotal(problem);
            return pigletMapper.updateById(p);
        }
        Piglet p = new Piglet();
        p.setPId(pId);
        Integer problem;
        if(null == piglet.getPProblemTotal()){
            problem = result;
        }
        else{
            problem = piglet.getPProblemTotal() - result;
        }
        p.setPProblemTotal(problem);
        return pigletMapper.updateById(p);
    }

    /***
     * 修改修改量
     * @param p
     * @return
     */
    @Override
    public int modifyPiglet(Piglet p) {
        return pigletMapper.updateById(p);
    }
}
