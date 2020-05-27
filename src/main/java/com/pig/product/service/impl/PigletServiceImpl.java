package com.pig.product.service.impl;

import com.pig.product.entity.Piglet;
import com.pig.product.mapper.PigletMapper;
import com.pig.product.service.IPigletService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
