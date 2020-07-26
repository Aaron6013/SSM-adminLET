package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * 查询所有用户
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAllUser(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "4")Integer size){
        ModelAndView modelAndView = new ModelAndView();
        List<UserInfo> userInfos = userService.findAllUser(page,size);
        PageInfo pageInfo = new PageInfo(userInfos);
        modelAndView.addObject("userInfos",pageInfo);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    /**
     * 保存用户
     * @param userInfo
     * @return
     */
    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username == 'Aaron'")
    public String saveUser(UserInfo userInfo){
        userService.saveUser(userInfo);
        return "redirect:findAll.do";
    }

    /**
     * 根据用户id查询对应用户
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findById(String id){
        ModelAndView modelAndView = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        modelAndView.addObject("user",userInfo);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }

    /**
     * 根据用户id查询用户及其可以添加的角色
     * @param userId
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId){
        //1、根据用户id查用户
        UserInfo userInfo = userService.findById(userId);
        //2、根据用户id查询可以添加的角色
        List<Role> roles = userService.findOtherRole(userId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",userInfo);
        modelAndView.addObject("roleList",roles);
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }

    /**
     * 向指定用户添加角色
     * @param userId 指定的用户
     * @param roleIds 要添加的角色
     * @return
     */
    @RequestMapping("/addRoleToUser.do")
    @PreAuthorize("authentication.principal.username == 'Aaron'")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) String[] roleIds){
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }
}
