package com.pig.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pig.product.entity.Pigsite;
import com.pig.product.mapper.PigsiteMapper;
import com.pig.product.service.IPigsiteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 猪场管理 服务实现类
 * </p>
 *
 * @author ST
 * @since 2020-05-25
 */
@Service
public class PigsiteServiceImpl extends ServiceImpl<PigsiteMapper, Pigsite> implements IPigsiteService {

    @Resource
    private PigsiteMapper pigsiteMapper;

    /***
     * 添加修改猪场
     * @param pigsite
     * @return
     */
    @Override
    public int addOrModifyPigsite(Pigsite pigsite) {
        if(null == pigsite.getPId()){
           return pigsiteMapper.insert(pigsite);
        }else{
            return pigsiteMapper.updateById(pigsite);
        }
    }

    /***
     * 查询所有猪场
     * @return
     */
    @Override
    public List getPigsiteList(String pname) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(null != pname){
            queryWrapper.like("p_name",pname);
        }
        return pigsiteMapper.selectList(queryWrapper);
    }
}
