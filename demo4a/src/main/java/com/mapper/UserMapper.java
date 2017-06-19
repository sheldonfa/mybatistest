package com.mapper;

import com.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public interface UserMapper {

    Map<String,User> findMap3();

    @MapKey(value = "id")
    Map<Integer,User> findMap4();
}
