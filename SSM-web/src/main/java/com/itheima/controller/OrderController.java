package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Order;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/25 - 10:02
 */
@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 查询所有订单
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAllOrders(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size){
        ModelAndView modelAndView = new ModelAndView();
        List<Order> orders = orderService.findAllOrders(page,size);
        //pageInfo是一个分页bean
        PageInfo pageInfo = new PageInfo<>(orders);
        modelAndView.addObject("orderInfo",pageInfo);
        modelAndView.setViewName("orders-list");
        return modelAndView;
    }

    /**
     * 根据id查询对应订单
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String id){
        ModelAndView modelAndView = new ModelAndView();
        Order order = orderService.findById(id);
        modelAndView.addObject("orders",order);
        modelAndView.setViewName("order-show");
        return modelAndView;
    }
}
