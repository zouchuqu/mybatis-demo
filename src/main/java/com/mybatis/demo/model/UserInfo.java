package com.mybatis.demo.model;

import lombok.Data;

import java.util.List;

/**
 * @auther kklu
 * @date 2019/9/29 15:01
 * @describe
 */
@Data
public class UserInfo {
    private Long id;
    private String username;
    private String password;
    private Integer high;
    private Integer age;
    private Integer sex;
    //一个用户能创建多个订单，用户和订单构成一对多的关系
    private List<Orders> orders;
    //一个用户可以有多个角色，即一个用户可以是会员同时也是管理员
//    private List<SysRole> sysRoleList;
}
