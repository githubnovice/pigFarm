package com.pig.product.service.impl;

import com.pig.product.entity.Menu;
import com.pig.product.mapper.MenuMapper;
import com.pig.product.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
