package com.mybatis.demo.mapper;

import com.mybatis.demo.model.SysRole;
import com.mybatis.demo.model.UserInfo;

import java.util.List;


/**
 * @auther kklu
 * @date 2019/10/14 12:18
 * @describe
 */

public interface UserInfoMapper {
    //一对多查询方式一
    //根据用户id查询用户信息，以及用户下面的所有订单信息
    UserInfo selectUserInfoAndOrdersByUserId(int UserId);
    //一对多查询方式二
    UserInfo getUserInfoAndOrdersByUserInfoId(int UserId);

    //多对多查询--给定角色id，查询这个角色所属的所有用户信息
    List<UserInfo> selectUserInfoByRoleId(int roleId);
    //多对多查询--给定用户id，查询这个用户所拥有的角色信息
    List<SysRole> selectSysRoleByUserInfoId(int userId);

}
