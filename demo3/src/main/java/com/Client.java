package com;


import com.mapper.UserMapper;
import com.pojo.User2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class Client {

    @Autowired
    private UserMapper userMapper;


    /**
     * 查询全部
     */
    @Test
    public void test1(){
        List<User2> user2s = userMapper.selectAll();
    }

    /**
     * 查询日期之间的列表
     */
    @Test
    public void test2(){
        Example example = new Example(User2.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLessThan("birthday","2017-06-25");
        criteria.andGreaterThanOrEqualTo("birthday","2017-06-16");
        List<User2> user2s = userMapper.selectByExample(example);
        for(User2 u: user2s){
            System.out.println(u.getUsername());
        }
    }
}
