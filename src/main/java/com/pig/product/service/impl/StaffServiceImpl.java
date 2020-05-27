package com.pig.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pig.product.entity.Staff;
import com.pig.product.mapper.StaffMapper;
import com.pig.product.service.IStaffService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 员工管理 服务实现类
 * </p>
 *
 * @author ST
 * @since 2020-05-23
 */
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements IStaffService {

    @Resource
    private StaffMapper staffMapper;

    /***
     * 查询本场员工
     * @param pid
     * @param sstate
     * @return
     */
    @Override
    public List getStaffByList(Integer pid, Integer sstate) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("p_id",pid);
        queryWrapper.eq("s_state",sstate);
        return staffMapper.selectList(queryWrapper);
    }

    /***
     * 添加员工
     * @param staff
     * @return
     */
    @Override
    public int addOrModifyStaff(Staff staff) {
        if(null == staff.getSId()){
            staff.setSEntryTime(new Date());
            return staffMapper.insert(staff);
        }else{
            return staffMapper.updateById(staff);
        }
    }
}
