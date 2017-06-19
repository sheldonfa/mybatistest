package com;


import com.mapper.UserMapper;
import com.pojo.User;
import com.resulthandler.MyMapResultHandler;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.MyBatisSystemException;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class Client {

    @Autowired
    private SqlSessionFactoryBean sqlSessionFactoryBean;

    @Autowired
    private UserMapper userMapper;

    private SqlSession getSession() throws Exception {
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        return sqlSessionFactory.openSession();
    }

    /**
     * 返回Map，含有多个kv，并且自定义KV
     * 方案：用自定义resultHandler实现的
     *
     * 评价：虽然实现了，但是没有用mapper的动态代理，感觉比较繁琐
     */
    @Test
    public void test1() throws Exception {
        MyMapResultHandler resultHandler = new MyMapResultHandler();
        SqlSession sqlSession = getSession();
        sqlSession.select("com.mapper.UserMapper.findMap",resultHandler);
        Map mapResults = resultHandler.getMapResults();
        System.out.println(mapResults);
    }

    /**
     * 如果想返回Map集合，key 自定义，value是对象，可以用selectMap方法。
     * 参数1：方法全限定名称
     * 参数2：指定key字段
     *
     * 评价：返回类型就是你的value，这个方式其实挺好了。但是也不是mapper代理的方式
     */
    @Test
    public void test2() throws Exception {
        SqlSession session = getSession();
        Map<Integer, User> map = session.selectMap("com.mapper.UserMapper.findMap2", "id");
        for(Map.Entry<Integer,User> entry: map.entrySet()){
            System.out.println(entry.getKey()+"  "+ entry.getValue());
        }
    }

    /**
     * 下面这种写法，其实我是希望mybatis调用selectMap方法，但是它调用了selectOne
     *
     */
    @Test(expected = MyBatisSystemException.class)
    public void test3(){
        Map<String,User> map =  userMapper.findMap3();
        System.out.println(map);
    }

    /**
     * 阅读源码发现，我们少了MapKey注解，所以系统不认为，我们要返回一个Map集合，反而认为我们是要返回一个User对象，这个对象的所有字段用一个Map集合装配。
     * 为方法添加@MapKey注解，就可以使用selectMap啦
     *
     */
    @Test
    public void test4(){
        Map<Integer, User> map4 = userMapper.findMap4();
        System.out.println(map4);
    }
}
