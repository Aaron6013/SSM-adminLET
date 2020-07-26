package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/25 - 18:02
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("findAll.do")
    public ModelAndView findAllRole(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "4")Integer size){
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roles = roleService.findAllRole(page,size);
        PageInfo pageInfo = new PageInfo(roles);
        modelAndView.addObject("roleInfo",pageInfo);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping("/save.do")
    public String addRole(Role role){
        roleService.addRole(role);
        return "redirect:findAll.do";
    }

    /**
     * 根据id查询对应角色
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView modelAndView = new ModelAndView();
        Role role = roleService.findById(id);
        modelAndView.addObject("role",role);
        modelAndView.setViewName("role-show");
        return modelAndView;
    }

    /**
     * 根据id删除角色
     * @param roleId
     * @return
     */
    @RequestMapping("/deleteById.do")
    public String deleteById(@RequestParam(name = "id",required = true) String roleId){
        roleService.deleteById(roleId);
        return "redirect:findAll.do";
    }

    /**
     * 根据角色id查询对应的角色及其可添加的权限
     * @param roleId
     * @return
     */
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) String roleId){
        //根据roleId查询role
        Role role = roleService.findById(roleId);
        //查询可以添加的权限
        List<Permission> permissions = roleService.findOtherPermission(roleId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("role",role);
        modelAndView.addObject("permissionList",permissions);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }

    /**
     * 向角色添加权限
     * @param roleId
     * @param permissionIds
     * @return
     */
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String roleId,@RequestParam(name = "ids",required = true) String[] permissionIds){
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }
}
