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
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername("Jack");
            userInfo.setSex(1);
            List<UserInfo> result = userInfoMapper.selectUserInfoByUsernameAndSex(userInfo);
            log.info("result={}", result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }


    //根据 id 更新 user 表的数据
    @Test
    public void testUpdateUserInfoById() {
        try {
            //如果设置的 id不存在，那么数据库没有数据更改
            UserInfo userInfo = new UserInfo();
            userInfo.setId(4L);
            userInfo.setUsername("令狐冲");
//            userInfo.setSex(1);
            userInfoMapper.updateUserInfoById(userInfo);
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
    public void testSelectUserInfoByChoose() {
        try {
            UserInfo userInfo = new UserInfo();
//            userInfo.setId(4L);
//            userInfo.setUsername("Text");
            userInfo.setSex(1);
            List<UserInfo> listUser = userInfoMapper.selectUserInfoByChoose(userInfo);
            for (UserInfo info : listUser) {
                log.info("info={}", info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserByListId() {
        try {
            UserInfoForm userInfoForm = new UserInfoForm();
            List<Integer> ids = new ArrayList<>();
            ids.add(1);
            ids.add(2);
            ids.add(3);
            ids.add(50);
            userInfoForm.setIds(ids);
            List<UserInfo> listUser = userInfoMapper.selectUserByListId(userInfoForm);
            for (UserInfo info : listUser) {
                log.info("info={}", info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }


    @Test
    public void testSelectUserInfoById() {
        try {
            UserInfo listUser = userInfoMapper.selectUserInfoById(4);
            log.info("info={}", listUser);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserInfoAll() {
        try {
            List<UserInfo> listUser = userInfoMapper.selectUserInfoAll();
            for (UserInfo info : listUser) {
                log.info("info={}", info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }


    @Test
    public void testSelectLikeUserName() {
        try {
            List<UserInfo> listUser = userInfoMapper.selectLikeUserName("明");
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
    public void testInsertUserInfo() {
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername("王二");
            userInfo.setPassword("123456");
            userInfo.setSex(1);
            userInfo.setAge(21);
            userInfo.setHigh(175);
            userInfoMapper.insertUserInfo(userInfo);
            //提交插入的数据
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
            userInfoMapper.deleteUserInfoById(1);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }
}
