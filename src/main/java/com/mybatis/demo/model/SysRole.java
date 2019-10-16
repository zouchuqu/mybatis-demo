package com.mybatis.demo.model;

import lombok.Data;

import java.util.List;

/**
 * @auther kklu
 * @date 2019/10/16 16:51
 * @describe
 */
@Data
public class SysRole {
    private Long id;
    private String name;
    //一个角色可以包含多个用户，即一个角色可以属于多个用户
    private List<UserInfo> userInfoList;
}
