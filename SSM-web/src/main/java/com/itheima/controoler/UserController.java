package com.itheima.controoler;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.UserInfo;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/25 - 15:19
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAllUser(@RequestParam(name = "page",required = true,defaultValue = "1")int page, @RequestParam(name = "size",required = true,defaultValue = "4")int size){
        ModelAndView modelAndView = new ModelAndView();
        List<UserInfo> userInfos = userService.findAllUser(page,size);
        PageInfo pageInfo = new PageInfo(userInfos);
        modelAndView.addObject("userInfos",pageInfo);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }
}
