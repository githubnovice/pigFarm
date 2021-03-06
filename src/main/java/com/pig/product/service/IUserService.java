package com.pig.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig.product.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ST
 * @since 2020-05-06
 */
public interface IUserService extends IService<User> {

    User userLogin(User user);

    int useRegister(User user);

    void checkToken(String token);

    List getUserList(Integer pid);

    int addOrModifyUserByPid(User user);

    User getUserByuAccount(String uAccount);

    Page getAdminUser(Integer page, Integer limit, Integer ustatus);

    int toExamineAdmin(Long uid, Integer uStatus);
}
