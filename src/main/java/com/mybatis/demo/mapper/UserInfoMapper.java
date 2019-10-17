package com.mybatis.demo.mapper;

import com.mybatis.demo.model.UserInfo;
import com.mybatis.demo.model.UserInfoForm;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther kklu
 * @date 2019/10/14 12:18
 * @describe
 */
@Repository
public interface UserInfoMapper {
    /**
     * 通过分页获取用户
     * @return
     */
    List<UserInfo> selectUserInfoByPage();

}
