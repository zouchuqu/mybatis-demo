package com.mybatis.demo.mapper;

import com.github.pagehelper.PageHelper;
import com.mybatis.demo.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @auther kklu
 * @date 2019/9/29 15:49
 * @describe
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserInfoMapperTest {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void testSelectUserInfoByUsernameAndSex() {
        try {
            //分页参数
            int page = 0;
            int size = 2;
            PageHelper.startPage(page, size);
            List<UserInfo> result = userInfoMapper.selectUserInfoByPage();
            log.info("result.size()={}--result={}", result.size(), result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
