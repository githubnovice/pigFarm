package com.pig.product.service.impl;

import com.pig.product.entity.Material;
import com.pig.product.mapper.MaterialMapper;
import com.pig.product.service.IMaterialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
