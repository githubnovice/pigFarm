package com.pig.product.controller;

import com.pig.product.entity.Staff;
import com.pig.product.service.IStaffService;
import com.pig.product.util.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * 员工管理
 */
@RestController
@RequestMapping("/staff/")
@CrossOrigin
public class StaffController extends BaseController {

    @Autowired
    private IStaffService iStaffService;

    /***
     * 员工添加
     * @param staff  员工对象
     * @return
     */
    @PostMapping("addOrModifyStaff")
    private Object addOrModifyStaff(Staff staff){
        int bool = iStaffService.addOrModifyStaff(staff);
        return bool == 1 ? renderSuccess("成功") : renderError("失败");
    }

    /***
     * 查询本场员工
     * @param pid     猪场ID
     * @param sstate  在职状态
     * @return
     */
    @GetMapping("getStaffByList")
    public Object getStaffByList(Integer pid,Integer sstate){
        List list = iStaffService.getStaffByList(pid,sstate);
        return list.size() > 0 ? renderSuccess(list) : renderError("暂无员工");
    }

    public Object deleteStaffByListId(Integer []sid){
        return null;
    }

    /***
     * 查询本场员工总数
     * @param pid
     * @param ulevel
     * @return
     */
    @PostMapping("getStaffCount")
    public Object getStaffCount(Integer pid,Integer ulevel){
        int count = iStaffService.getStaffCount(pid,ulevel);
        return renderSuccess(count);
    }
}
