package com.mybatis.demo.mapper;

import com.mybatis.demo.model.UserInfo;
import com.mybatis.demo.model.UserInfoForm;

import java.util.List;

/**
 * @auther kklu
 * @date 2019/10/14 12:18
 * @describe
 */

public interface UserInfoMapper {

    //根据用户名和性别查询
    List<UserInfo> selectUserInfoByUsernameAndSex(UserInfo userInfo) throws Exception;

    //根据 id 修改 user_info 表数据
    void updateUserInfoById(UserInfo userInfo) throws Exception;

    //根据某一个条件查询
    List<UserInfo> selectUserInfoByChoose(UserInfo userInfo) throws Exception;

    //根据一组用户 id 查询用户
    List<UserInfo> selectUserByListId(UserInfoForm userInfoForm) throws Exception;

    //根据 id 查询 user_info 表数据
    UserInfo selectUserInfoById(int id) throws Exception;

    //查询 user_info 表所有数据
    List<UserInfo> selectUserInfoAll() throws Exception;

    //根据 id 查询 user_info 表数据
    List<UserInfo> selectLikeUserName(String username) throws Exception;

    //向 user_info 表插入一条数据
    void insertUserInfo(UserInfo userInfo) throws Exception;

    //根据 id 删除 user_info 表数据
    void deleteUserInfoById(int id) throws Exception;

}
