package com.pig.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig.product.entity.User;
import com.pig.product.service.IUserService;
import com.pig.product.util.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * 用户管理
 */
@RestController
@RequestMapping("/user/")
@CrossOrigin
public class UserController extends BaseController {

    @Autowired
    private IUserService iUserService;

    /***
     * 查询本场管理员
     * @return
     */
    @GetMapping("getUserList")
    public Object getUserList(Integer pid){
        List list = iUserService.getUserList(pid);
        return list.size() > 0 ? renderSuccess(list) : renderError("暂无数据");
    }

    /***
     * 添加修改管理员
     * @param user
     * @return
     */
    @PostMapping("addOrModifyUserByPid")
    public Object addOrModifyUserByPid(User user){
        User users = iUserService.getUserByuAccount(user.getUAccount());
        if(null == user.getUId()){
            if(null != users){
                return renderError("该手机号已被注册");
            }
        }
        int bool = iUserService.addOrModifyUserByPid(user);
        return 1 == bool ? renderSuccess("操作成功") : renderError("操作失败");
    }

    /***
     * 查询不可使用管理员
     * @return
     */
    @GetMapping("getAdminUser")
    public Object getAdminUser(Integer page,Integer limit,Integer ustatus){
        Page pages = iUserService.getAdminUser(page,limit,ustatus);
        return renderSuccess(pages.getTotal(),pages.getRecords());
    }

    /***
     * 审核申请管理员
     * @param uStatus
     * @return
     */
    @PostMapping("toExamineAdmin")
    public Object toExamineAdmin(Long uid,Integer uStatus){
        int bool = iUserService.toExamineAdmin(uid,uStatus);
        return bool == 1 ? renderSuccess("激活成功") : renderError("激活失败");
    }
}
