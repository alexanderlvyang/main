package org.yang.suibian.serviceimpl;

import org.springframework.stereotype.Service;
import org.yang.suibian.mapper.demo.UserMapper;
import org.yang.suibian.model.demo.User;
import org.yang.suibian.service.UserService;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public int addUser(String name) {
        User user=User.builder().name(name).build();
        return userMapper.insert(user);
    }
}
