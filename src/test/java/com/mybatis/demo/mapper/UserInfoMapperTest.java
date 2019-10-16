package com.mybatis.demo.mapper;

import com.mybatis.demo.model.SysRole;
import com.mybatis.demo.model.UserInfo;
import com.mybatis.demo.form.UserInfoForm;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
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
    private static SqlSessionFactory sqlSessionFactory;
    private static SqlSession sqlSession = null;
    private static UserInfoMapper userInfoMapper;

    @BeforeClass
    public static void init() {
        try {
            //将工具类读入 reader
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            //创建 SqlSessionFactory 对象，该对象包含了mybatis-config.xml相关配置信息
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSession = sqlSessionFactory.openSession();
            userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
            reader.close();
        } catch (IOException ignore) {
            ignore.printStackTrace();
        }
    }


    @Test
    public void testSelectUserInfoByUsernameAndSex() {
        try {
            UserInfo result = userInfoMapper.selectUserInfoAndOrdersByUserId(2);
            log.info("result={}", result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }


    @Test
    public void testGetUserInfoAndOrdersByUserInfoId() {
        try {
            UserInfo result = userInfoMapper.getUserInfoAndOrdersByUserInfoId(2);
            log.info("result={}", result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    //多对多查询--给定角色id，查询这个角色所属的所有用户信息
    @Test
    public void testSelectUserInfoByRoleId() {
        try {
            List<UserInfo> result = userInfoMapper.selectUserInfoByRoleId(3);
            log.info("result={}", result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    //多对多查询--给定用户id，查询这个用户所拥有的角色信息
    @Test
    public void testSelectSysRoleByUserInfoId() {
        try {
            List<SysRole> result = userInfoMapper.selectSysRoleByUserInfoId(4);
            log.info("result={}", result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

}
