package com;


import com.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class Client {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void test(){
        Map<Integer, String> map = findAll();
        System.out.println(map);
    }

    private Map<Integer,String> findAll() {
        List<Map<Integer, String>> all = userMapper.findAll();
        Map<Integer,String> map = new HashMap<Integer, String>();
        for(Map<Integer,String> a: all){
            for(Map.Entry<Integer,String> entry: a.entrySet()){
                map.put(entry.getKey(),entry.getValue());
            }
        }
        return map;
    }
}
