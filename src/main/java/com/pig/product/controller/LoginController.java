package com.pig.product.controller;

import com.pig.product.entity.User;
import com.pig.product.service.IUserService;
import com.pig.product.util.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login/")
@CrossOrigin
public class LoginController extends BaseController {

    @Autowired
    private IUserService iUserService;

    /***
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("userLogin")
    public Object userLogin(User user){
        User obj = iUserService.userLogin(user);
        if(null != obj && 2 == obj.getUStatus()){
            return renderError("账号暂未激活，请联系管理员");
        }else if(null != obj && 0 == obj.getUStatus()){
            return renderError("账号已被禁用，请联系管理员");
        }else{
            return null != obj ? renderSuccess(obj) : renderError("账户或密码错误");
        }
    }

    /***
     * 注册用户
     * @return
     */
    @PostMapping("useRegister")
    public Object useRegister(User user){
        int bool = iUserService.useRegister(user);
        if(2 == bool){
            return renderError("该账户已存在");
        }else if(500 == bool){
            return renderError("系统异常");
        }else{
            return 1 == bool ? renderSuccess("注册成功，审核中") : renderError("注册失败");
        }
    }

    @PostMapping("checkToken")
    public Object checkToken(String token){
        iUserService.checkToken(token);
        return null;
    }
}