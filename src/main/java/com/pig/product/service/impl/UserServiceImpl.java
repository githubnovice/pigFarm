package com.pig.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig.product.entity.User;
import com.pig.product.mapper.UserMapper;
import com.pig.product.service.IUserService;
import com.pig.product.util.MD5Util;
import com.pig.product.util.RedisUtil;
import com.pig.product.util.Token;
import com.pig.product.util.UniqueNumber;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ST
 * @since 2020-05-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    /***
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public User userLogin(User user) {
        if(null != user.getUAccount() && null != user.getUPassword()){
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("u_account",user.getUAccount());
            wrapper.eq("u_password",MD5Util.getMD5(user.getUPassword()));
            User obj = userMapper.selectOne(wrapper);
            if(null != obj && 1 == obj.getUStatus()){
                RedisUtil.setObject(user.getUAccount(),Token.getToken());
                User uobj = new User();
                uobj.setUId(obj.getUId());
                uobj.setUCreateTime(obj.getUCreateTime());
                uobj.setULastLoginTime(new Date());
                if(null != obj.getULoginCount()){
                    uobj.setULoginCount(obj.getULoginCount()+1);
                }else{
                    uobj.setULoginCount(1L);
                }
                uobj.setUToken(Token.getToken());
                userMapper.updateById(uobj);
                return obj;
            }else {
                return obj;
            }
        }else{
            return null;
        }
    }


    /***
     * 注册账户
     * @param user
     * @return
     */
    @Override
    public int useRegister(User user) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("u_account",user.getUAccount());
        try {
            User obj = userMapper.selectOne(wrapper);
            if(null == obj){
                user.setUNumber(UniqueNumber.makeOrderNum());
                user.setUPassword(MD5Util.getMD5(user.getUPassword()));
                user.setULevel(2);
                user.setUCreateTime(new Date());
                return userMapper.insert(user);
            }else{
                return 2;
            }
        }catch (Exception e){
            return 500;
        }
    }

    /***
     * 校验token
     * @param token
     */
    @Override
    public void checkToken(String token) {

    }

    /***
     * 查询所有用户
     * @return
     */
    @Override
    public List getUserList() {
        List list = userMapper.selectList(null);
        return list;
    }
}
