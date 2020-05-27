package com.pig.product.service;

import com.pig.product.entity.Staff;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 员工管理 服务类
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
public interface IStaffService extends IService<Staff> {

    List getStaffByList(Integer pid, Integer sstate);

    int addOrModifyStaff(Staff staff);
}
