package com.atxzy.ssm.web.controller;

import com.atxzy.ssm.domain.Permission;
import com.atxzy.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    IPermissionService permissionService;

    @RequestMapping("/save.do")
    public String save(Permission permission){

        permissionService.save(permission);
        return "redirect:/permission/findAll.do";
    }


    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<Permission> all = permissionService.findAll();
        modelAndView.addObject("permissionList",all);
        modelAndView.setViewName("permission-list");

        return modelAndView;
    }
}
