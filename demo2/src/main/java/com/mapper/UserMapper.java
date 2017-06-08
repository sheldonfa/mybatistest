package com.mapper;

import com.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserMapper {

    User findUserById(Integer id);

    List<User> findAll();
}
