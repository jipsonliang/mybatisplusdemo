package com.jipson.mp.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jipson.mp.pojo.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends BaseMapper<User> {

    User findById(Long id);

}
