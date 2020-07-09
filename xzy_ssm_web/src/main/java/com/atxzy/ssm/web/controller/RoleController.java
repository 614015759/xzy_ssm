package com.atxzy.ssm.web.controller;

import com.atxzy.ssm.domain.Permission;
import com.atxzy.ssm.domain.Role;
import com.atxzy.ssm.service.IRoleService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/role")
@Controller
public class RoleController {

    @Autowired
    IRoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roles = roleService.findAll();
        modelAndView.addObject("roleList",roles);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    @RequestMapping("/save.do")
    public String save(Role role){
        roleService.save(role);
        return "redirect:/role/findAll.do";
    }


    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(required = true,name = "id") String roleId){
            ModelAndView modelAndView = new ModelAndView();
            Role role = roleService.findById(roleId);
            modelAndView.addObject("role",role);
            modelAndView.setViewName("role-show");
            return modelAndView;
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) String id){
        ModelAndView modelAndView = new ModelAndView();
        Role role = roleService.findById(id);
        List<Permission> permissions = roleService.findOtherPermissions(id);
        modelAndView.addObject("role",role);
        modelAndView.addObject("permissionList",permissions);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(String roleId,String[] ids){

        roleService.addPermissionToRole(roleId,ids);

        return "redirect:/role/findAll.do";
    }
}
