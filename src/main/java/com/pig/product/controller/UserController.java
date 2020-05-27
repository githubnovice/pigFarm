package com.pig.product.controller;

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

    @GetMapping("getUserList")
    public Object getUserList(){
        List list = iUserService.getUserList();
        return list.size() > 0 ? renderSuccess(list) : renderError("暂无数据");
    }
}
