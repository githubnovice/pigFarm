package com.pig.product.service;

import com.pig.product.entity.Pigsite;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 猪场管理 服务类
 * </p>
 *
 * @author ST
 * @since 2020-05-25
 */
public interface IPigsiteService extends IService<Pigsite> {

    int addOrModifyPigsite(Pigsite pigsite);

    List getPigsiteList(String pname);
}
