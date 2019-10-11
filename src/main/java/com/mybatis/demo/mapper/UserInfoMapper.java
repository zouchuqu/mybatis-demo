package com.mybatis.demo.mapper;

import com.mybatis.demo.model.UserInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @auther kklu
 * @date 2019/10/11 19:01
 * @describe
 */

public interface UserInfoMapper {
    //根据 id 查询 user_info 表数据
    @Select("select * from user_info where id = #{id}")
    UserInfo selectUserInfoById(int id) throws Exception;

    //查询 user_info 表所有数据
    @Select("select * from user_info")
    UserInfo selectUserInfoAll(int id) throws Exception;

   //根据 id 查询 user_info 表数据
    @Select("select * from user_info where username like '%${value}%'")
    UserInfo selectLikeUserName(String username) throws Exception;

    //向 user_info 表插入一条数据
    @Insert("insert into user_info(username,password) value(#{username},#{password})")
    void insertUserInfo(UserInfo userInfo) throws Exception;

    //根据 id 修改 user_info 表数据
    @Update("update user_info set username=#{username} where id=#{id}")
    void updateUserInfoById(UserInfo userInfo) throws Exception;

    //根据 id 删除 user_info 表数据
    @Delete("delete from user_info where id=#{id}")
    void deleteUserInfoById(int id) throws Exception;
}

