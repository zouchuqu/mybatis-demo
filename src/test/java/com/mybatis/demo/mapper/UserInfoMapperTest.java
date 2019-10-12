package com.mybatis.demo.mapper;

import com.mybatis.demo.model.UserInfo;
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
    private static SqlSession sqlSession ;
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


    //根据id查询user表数据
    @Test
    public void testSelectUserInfoById() {
        try {
            UserInfo userInfo = userInfoMapper.selectUserInfoById(1);
            log.info("userInfo={}", userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    //查询所有user表所有数据
    @Test
    public void testSelectUserInfoAll() {
        try {
            List<UserInfo> listUser = userInfoMapper.selectUserInfoAll();
            for (UserInfo userInfo : listUser) {
                log.info("userInfo={}", userInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    //模糊查询：根据 user 表的username字段
    @Test
    public void testSelectLikeUserName() {
        try {
            List<UserInfo> listUser = userInfoMapper.selectLikeUserName("J");
            for (UserInfo userInfo : listUser) {
                log.info("userInfo={}", userInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    //向 user 表中插入一条数据
    @Test
    public void testInsertUser() {
        try {
            UserInfo user = new UserInfo();
            user.setUsername("黎明");
            user.setPassword("123456");
            userInfoMapper.insertUserInfo(user);
            //提交插入的数据
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    //根据 id 更新 user 表的数据
    @Test
    public void testUpdateUserById() {
        try {
            //如果设置的 id不存在，那么数据库没有数据更改
            UserInfo user = new UserInfo();
            user.setId(7L);
            user.setUsername("Jack");
            userInfoMapper.updateUserInfoById(user);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    //根据 id 删除 user 表的数据
    @Test
    public void testDeleteUserInfoById() {
        try {
            userInfoMapper.deleteUserInfoById(8);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }
}
