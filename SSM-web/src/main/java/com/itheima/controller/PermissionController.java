package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/25 - 18:23
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有权限
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAllPermission(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "4")Integer size){
        ModelAndView modelAndView = new ModelAndView();
        List<Permission> permissions = permissionService.findAllPermission(page, size);
        PageInfo pageInfo = new PageInfo(permissions);
        modelAndView.addObject("permissionInfo",pageInfo);
        modelAndView.setViewName("permission-list");
        return modelAndView;
    }

    /**
     * 保存权限
     * @param permission
     * @return
     */
    @RequestMapping("/save.do")
    public String addRole(Permission permission){
        permissionService.savePermission(permission);
        return "redirect:findAll.do";
    }

    /**
     * 根据id查询对应权限
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView modelAndView = new ModelAndView();
        Permission permission = permissionService.findById(id);
        modelAndView.addObject("permission",permission);
        modelAndView.setViewName("permission-show");
        return modelAndView;
    }

    /**
     * 根据权限id删除权限
     * @param permissionId
     * @return
     */
    @RequestMapping("/deleteById.do")
    public String deleteById(@RequestParam(name = "id",required = true) String permissionId){
        permissionService.deleteById(permissionId);
        return "redirect:findAll.do";
    }
}
