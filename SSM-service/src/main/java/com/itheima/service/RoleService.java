package com.itheima.service;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/25 - 18:05
 * 角色业务层
 */
public interface RoleService {

    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAllRole(int page, int size);

    /**
     * 添加角色
     * @param role
     */
    void addRole(Role role);

    /**
     * 根据id查询对应角色
     * @param id
     * @return
     */
    Role findById(String id);

    /**
     * 根据id删除角色
     * @param roleId
     */
    void deleteById(String roleId);

    /**
     * 根据角色id查询可以添加的权限
     * @param roleId
     * @return
     */
    List<Permission> findOtherPermission(String roleId);

    /**
     * 向角色添加权限
     * @param roleId
     * @param permissionIds
     */
    void addPermissionToRole(String roleId,String[] permissionIds);
}
