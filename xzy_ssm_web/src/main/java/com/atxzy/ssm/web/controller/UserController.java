package com.atxzy.ssm.web.controller;

import com.atxzy.ssm.domain.Role;
import com.atxzy.ssm.domain.UserInfo;
import com.atxzy.ssm.service.IUserService;
import com.atxzy.ssm.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView modelAndView = new ModelAndView();
        List<UserInfo> userInfos = userService.findAll();
        modelAndView.addObject("userList", userInfos);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("/save.do")
    public String saveUser(UserInfo userInfo) {
        ModelAndView modelAndView = new ModelAndView();
        userService.saveUser(userInfo);

        return "redirect:/user/findAll.do";
    }


    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String userId) {
        System.out.println(userId);
        ModelAndView modelAndView = new ModelAndView();
        UserInfo userInfo = userService.findById(userId);
        modelAndView.addObject("user",userInfo);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }


    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView  findUserByIdAndAllRole(String id){
        ModelAndView modelAndView = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        List<Role> otherRoles = userService.findOtherRoles(id);
        modelAndView.addObject("user",userInfo);
        modelAndView.addObject("roleList",otherRoles);
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) String[] roleIds){

        userService.addRoleToUser(userId,roleIds);

        return "redirect:/user/findAll.do";
    }
}
