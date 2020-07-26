package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/25 - 9:21
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查询所有产品
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAllProduct(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "4")Integer size){
        ModelAndView modelAndView = new ModelAndView();
        List<Product> products = productService.findAllProduct(page,size);
        PageInfo pageInfo = new PageInfo(products);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }

    /**
     * 保存产品
     * @param product
     * @return
     */
    @RequestMapping("/save.do")
    public String saveProduct(Product product){
        productService.saveProduct(product);
        return "redirect:findAll.do";
    }
}
