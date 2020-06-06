package com.pig.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.Usemateriallog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 材料使用日志 服务类
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
public interface IUsemateriallogService extends IService<Usemateriallog> {

    int addOrModifyUseMaterial(Usemateriallog usemateriallog);

    Page getUseMaterialByList(Integer curren, Integer size, Integer pid, String purpose);

    Usemateriallog getUseMaterialById(Long id);
}
