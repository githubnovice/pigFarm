package com.pig.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.Troublepig;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;

/**
 * <p>
 * 生猪异常 服务类
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
public interface ITroublepigService extends IService<Troublepig> {

    Page getTroublepigByList(Integer curren, Integer size, Integer pid, String pnumber, Integer state);

    int addOrModifyTroublepig(Troublepig troublepig, String findtime) throws ParseException;

    Troublepig getTroublepigById(Long tip);

    int addDeathPid(Troublepig troublepig, Integer deathCont);

    int addDeathPig(Troublepig troublepig, String findtime) throws ParseException;
}
