package com.pig.product.controller;

import com.pig.product.entity.User;
import com.pig.product.service.IUserService;
import com.pig.product.util.BaseController;
import com.pig.product.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/***
 * 用户登录
 */
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
    public Object userLogin(User user) {
        User obj = iUserService.userLogin(user);
        if (null == obj)
            return renderError("账户或密码错误");
        if (2 == obj.getUStatus())
            return renderError("账号暂未激活，请联系管理员");
        if (0 == obj.getUStatus())
            return renderError("账号已被禁用，请联系管理员");
        return renderSuccess(obj);

    }

    /***
     * 注册用户
     * @return
     */
    @PostMapping("useRegister")
    public Object useRegister(User user) {
        int bool = iUserService.useRegister(user);
        if (2 == bool)
            return renderError("该账户已存在");
        if (500 == bool)
            return renderError("系统异常");
        return 1 == bool ? renderSuccess("注册成功，审核中") : renderError("注册失败");
    }

    /***
     * 验证是否登陆
     *
     * @param username
     * @param token
     * @return
     */
    @PostMapping("checkToken")
    public Object checkToken(String username, String token) {
        Object tokens = RedisUtil.getObject(username);
        if (null != tokens && "" != tokens) {
            if (tokens.equals(token)) {
                return renderSuccess();
            }
        }
        return renderError();
    }

    /***
     * 清除token
     *
     * @param username
     * @return
     */
    @PostMapping("cleanToken")
    public Object cleanToken(String username) {
        if (RedisUtil.getObject(username) != null) {
            RedisUtil.delkeyObject(username);
            return renderSuccess();
        }
        return renderError();
    }
}
