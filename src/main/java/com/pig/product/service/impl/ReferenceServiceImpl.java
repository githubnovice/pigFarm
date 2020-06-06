package com.pig.product.service.impl;

import com.pig.product.entity.Reference;
import com.pig.product.mapper.ReferenceMapper;
import com.pig.product.service.IReferenceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 材料参考 服务实现类
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
@Service
public class ReferenceServiceImpl extends ServiceImpl<ReferenceMapper, Reference> implements IReferenceService {

    @Resource
    private ReferenceMapper referenceMapper;

    /***
     * 添加修改参考材料记录
     * @param reference
     * @return
     */
    @Override
    public int addOrModifyReference(Reference reference) {
        if(null == reference.getRId()){
           return referenceMapper.insert(reference);
        }
        return referenceMapper.updateById(reference);
    }
}
