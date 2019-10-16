package com.mybatis.demo.mapper;

import com.mybatis.demo.model.Orders;
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

/**
 * @auther kklu
 * @date 2019/10/15 16:18
 * @describe
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrdersMapperTest {
    private static SqlSessionFactory sqlSessionFactory;
    private static SqlSession sqlSession = null;
    private static OrderMapper orderMapper;

    @BeforeClass
    public static void init() {
        try {
            //将工具类读入 reader
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            //创建 SqlSessionFactory 对象，该对象包含了mybatis-config.xml相关配置信息
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSession = sqlSessionFactory.openSession();
            orderMapper = sqlSession.getMapper(OrderMapper.class);
            reader.close();
        } catch (IOException ignore) {
            ignore.printStackTrace();
        }
    }


    @Test
    public void selectOrderAndUserInfoByOrderID() {
        try {
            Orders result = orderMapper.selectOrdersAndUserInfoByOrderID(1);
            log.info("result={}", result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void getOrdersByOrderId() {
        try {
            Orders result = orderMapper.getOrdersByOrderId(1);
            log.info("result={}", result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void getUserInfoByUserId() {
        try {
            UserInfo result = orderMapper.getUserInfoByUserId(2);
            log.info("result={}", result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }
}