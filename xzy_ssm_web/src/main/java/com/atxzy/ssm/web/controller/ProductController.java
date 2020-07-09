package com.atxzy.ssm.web.controller;

import com.atxzy.ssm.domain.Product;
import com.atxzy.ssm.service.IProductService;
import com.atxzy.ssm.service.ISystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {



    @Autowired
    IProductService productService;

    @RequestMapping("/findAll.do")
    @RolesAllowed("Admin")
    public ModelAndView findAll() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> ps = productService.findAll();

        modelAndView.addObject("productList",ps);
        modelAndView.setViewName("product-list");

        return modelAndView;
    }

    @RequestMapping("/save.do")
    public String save(Product product){
        productService.save(product);

        return "redirect:/product/findAll.do";

    }

}
