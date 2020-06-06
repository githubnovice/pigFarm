package com.pig.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.Material;
import com.pig.product.mapper.MaterialMapper;
import com.pig.product.service.IMaterialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig.product.util.UniqueNumber;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 材料进库 服务实现类
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements IMaterialService {

    @Resource
    private MaterialMapper materialMapper;

    /***
     * 添加材料Or修改材料
    * @param material
     * @return
     */
    @Override
    public int addOrModifyMaterial(Material material) {
        if(null == material.getMId()){
            material.setMNumber(UniqueNumber.makeOrderNum());
            material.setMAddTime(new Date());
            return materialMapper.insert(material);
        }
        return materialMapper.updateById(material);
    }

    /**
     * 查询本场材料
     * @param curren
     * @param size
     * @param pid
     * @param mname
     * @return
     */
    @Override
    public Page getMaterialList(Integer curren, Integer size, Integer pid, String mname) {
        if(null == curren){
            curren = 1;
        }
        if(null == size){
            size = 10;
        }
        Page page = new Page(curren,size);
        QueryWrapper queryWrapper = new QueryWrapper();
        if(null != mname){
            queryWrapper.like("m_name",mname);
        }
        queryWrapper.eq("p_id",pid);
        return materialMapper.selectPage(page,queryWrapper);
    }

    /***
     * 查询本场未使用完的材料
     * @param pid
     * @return
     */
    @Override
    public List getUnfinishedMaterialList(Integer pid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("p_id",pid);
        return materialMapper.selectList(queryWrapper);
    }

    /***
     * 根据材料唯一编号查询材料
     * @param mNumber
     * @return
     */
    @Override
    public Material getMaterialBymNumber(String mNumber) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("m_number",mNumber);
        return materialMapper.selectOne(queryWrapper);
    }
}
