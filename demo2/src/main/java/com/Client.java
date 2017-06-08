package com;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.UserMapper;
import com.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class Client {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void testPageHelper() {
        //分页处理，显示第一页的10条数据
        Page<User> page = PageHelper.startPage(1, 10);
        List<User> all = userMapper.findAll();
        // 取分页信息
        PageInfo<User> pageInfo = new PageInfo<User>(all);

        System.out.println(page.getTotal());
        System.out.println(pageInfo.getTotal());
        System.out.println(page.getCountSignal());
    }
}
