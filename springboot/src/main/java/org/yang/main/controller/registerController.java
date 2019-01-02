package org.yang.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller()
@RequestMapping("*.do")
public class registerController {
    @RequestMapping("register")
    @ResponseBody
    public String register(){

    }
}
