package com.pig.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.Piglet;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 猪仔进库 服务类
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
public interface IPigletService extends IService<Piglet> {

    Page getPigletByPid(Integer curren, Integer size, Integer pid, String pnumber);

    int addOrModifyPiglet(Piglet piglet);

    int modifyPigletOut(Piglet piglet);

    List getInPigByPid(Integer pid);

    Piglet getPigletByPNumber(String pNumber);

    int modifyPigletById(Long pId, Integer state, Integer result);

    int modifyPiglet(Piglet p);
}
