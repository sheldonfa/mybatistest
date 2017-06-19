package com;


import com.mapper.UserMapper;
import com.pojo.User;
import com.resulthandler.MyMapResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class Client {

    @Autowired
    private SqlSessionFactoryBean sqlSessionFactoryBean;

    @Autowired
    private UserMapper userMapper;

    /**
     * 返回map，含有多个kv
     */
    @Test
    public void test1() throws Exception {
        MyMapResultHandler resultHandler = new MyMapResultHandler();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.select("com.mapper.UserMapper.findMap",resultHandler);
        Map mapResults = resultHandler.getMapResults();
        System.out.println(mapResults);
    }
}
