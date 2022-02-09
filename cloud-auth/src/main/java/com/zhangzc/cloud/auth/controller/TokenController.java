package com.zhangzc.cloud.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/token")
public class TokenController {

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView, @RequestParam(required = false) String error) {
        modelAndView.setViewName("/ftl/login");
        modelAndView.addObject("error", error);
        return modelAndView;
    }
}
