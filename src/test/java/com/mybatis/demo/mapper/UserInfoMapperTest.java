package com.mybatis.demo.mapper;

import com.mybatis.demo.model.UserInfo;
import com.mybatis.demo.model.UserInfoForm;
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

    @BeforeClass
    public static void init() {
        try {
            //将工具类读入 reader
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            //创建 SqlSessionFactory 对象，该对象包含了mybatis-config.xml相关配置信息
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSession = sqlSessionFactory.openSession();
            reader.close();
        } catch (IOException ignore) {
            ignore.printStackTrace();
        }
    }


    //根据id查询user表数据
    @Test
    public void testSelectUserInfoByUsernameAndSex() {
        try {
        /*这个字符串由 userMapper.xml 文件中 两个部分构成
            <mapper namespace="com.mybatis.demo"> 的 namespace 的值
            <select id="selectUserById" > id 值*/
            /*可以单独写 <select id="selectUserById" > id 值*/

//            String statement = "com.mybatis.demo.selectUserInfoByUsernameAndSex";
            String statement = "selectUserInfoByUsernameAndSex";
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername("Jack");
            userInfo.setSex(1);
            List<UserInfo> result = sqlSession.selectList(statement, userInfo);
            log.info("result={}", result);
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectLikeUserName() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            List<UserInfo> listUser = sqlSession.selectList("selectLikeUserName", "%t%");
            for (UserInfo userInfo : listUser) {
                log.info("userInfo={}", userInfo);
            }
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserByListId() {
        try {
            UserInfoForm uv = new UserInfoForm();
            List<Integer> ids = new ArrayList<>();
            ids.add(1);
            ids.add(2);
            ids.add(3);
            ids.add(50);
            uv.setIds(ids);
            List<UserInfo> listUser = sqlSession.selectList("selectUserByListId", uv);
            for(UserInfo info : listUser){
                log.info("info={}", info);
            }
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }



    //向 user 表中插入一条数据
    @Test
    public void testInsertUserInfo() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserInfo user = new UserInfo();
            user.setUsername("Bob");
            user.setPassword("123456");
            sqlSession.insert("insertUserInfo", user);
            //提交插入的数据
            sqlSession.commit();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    //根据 id 更新 user 表的数据
    @Test
    public void testUpdateUserInfoById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            //如果设置的 id不存在，那么数据库没有数据更改
            UserInfo userInfo = new UserInfo();
            userInfo.setId(4L);
            userInfo.setUsername("Text");
//            userInfo.setSex(1);
            sqlSession.update("updateUserInfoById", userInfo);
            sqlSession.commit();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }
  //根据 id 更新 user 表的数据
    @Test
    public void testSelectUserInfoByChoose() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            //如果设置的 id不存在，那么数据库没有数据更改
            UserInfo userInfo = new UserInfo();
//            userInfo.setId(7L);
//            userInfo.setUsername("Text");
//            userInfo.setSex(1);
            List<UserInfo> listUser = sqlSession.selectList("selectUserInfoByChoose", userInfo);
            for (UserInfo info : listUser) {
                log.info("info={}", info);
            }
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }


    //根据 id 删除 user 表的数据
    @Test
    public void testDeleteUserInfoById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            sqlSession.delete("deleteUserInfoById", 6);
            sqlSession.commit();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }
}
