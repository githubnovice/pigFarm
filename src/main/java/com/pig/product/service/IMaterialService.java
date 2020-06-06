package com.pig.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.Material;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 材料进库 服务类
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
public interface IMaterialService extends IService<Material> {

    int addOrModifyMaterial(Material material);

    Page getMaterialList(Integer curren, Integer size, Integer pid, String mname);

    List getUnfinishedMaterialList(Integer pid);

    Material getMaterialBymNumber(String mNumber);
}
