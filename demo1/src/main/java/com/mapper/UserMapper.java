package com.mapper;

import com.pojo.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {

    User findUserById(Integer id);
}
