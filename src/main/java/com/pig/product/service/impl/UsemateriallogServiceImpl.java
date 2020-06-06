package com.pig.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.Usemateriallog;
import com.pig.product.mapper.UsemateriallogMapper;
import com.pig.product.service.IUsemateriallogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 材料使用日志 服务实现类
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
@Service
public class UsemateriallogServiceImpl extends ServiceImpl<UsemateriallogMapper, Usemateriallog> implements IUsemateriallogService {

    @Resource
    private UsemateriallogMapper usemateriallogMapper;

    /***
     * 添加Or修改使用材料
     * @param usemateriallog
     * @return
     */
    @Override
    public int addOrModifyUseMaterial(Usemateriallog usemateriallog) {
        if(null == usemateriallog.getId()){
            usemateriallog.setMUseTime(new Date());
            return usemateriallogMapper.insert(usemateriallog);
        }
        return usemateriallogMapper.updateById(usemateriallog);
    }

    /***
     * 查询本场材料使用记录
     * @param curren
     * @param size
     * @param pid
     * @param purpose
     * @return
     */
    @Override
    public Page getUseMaterialByList(Integer curren, Integer size, Integer pid, String purpose) {
        if(null == curren){
            curren = 1;
        }
        if(null == size){
            size = 10;
        }
        Page page = new Page(curren,size);
        if(null != purpose){
            purpose = "%"+purpose+"%";
        }
        page.setRecords(usemateriallogMapper.getUseMaterialByList(page,pid,purpose));
        return page;
    }

    /***
     * 根据ID查询 使用过的材料
     * @param id
     * @return
     */
    @Override
    public Usemateriallog getUseMaterialById(Long id) {
        return usemateriallogMapper.selectById(id);
    }
}
