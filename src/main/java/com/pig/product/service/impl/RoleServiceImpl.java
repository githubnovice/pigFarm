package com.pig.product.service.impl;

import com.pig.product.entity.Role;
import com.pig.product.mapper.RoleMapper;
import com.pig.product.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色管理 服务实现类
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
