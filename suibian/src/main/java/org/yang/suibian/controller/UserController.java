package org.yang.suibian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yang.suibian.service.UserService;

import javax.annotation.Resource;

@Controller
public class UserController {
    @Resource
    private UserService userServiceImpl;
    @RequestMapping("add")
    @ResponseBody
    public String add(String name){
        int insertStatus = userServiceImpl.addUser(name);
        return insertStatus+"success";
    }
}
