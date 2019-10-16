package com.mybatis.demo.model;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @auther kklu
 * @date 2019/10/15 15:21
 * @describe
 */
@Data
@Table("orders")
public class Orders {
    //订单ID
    private int id;
    //用户ID
    private Integer userId;
    //订单数量
    private String number;
    //和用户表构成一对一的关系，即一个订单只能由一个用户创建
    private UserInfo userInfo;
}
