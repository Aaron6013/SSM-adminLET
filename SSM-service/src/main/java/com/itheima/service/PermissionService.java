package com.itheima.service;

import com.itheima.domain.Permission;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/25 - 18:25
 * 权限业务层
 */
public interface PermissionService {

    /**
     * 查询所有权限
     */
    List<Permission> findAllPermission(int page, int size);

    /**
     * 保存权限
     * @param permission
     */
    void savePermission(Permission permission);

    /**
     * 根据id查询对应权限
     * @param id
     * @return
     */
    Permission findById(String id);

    /**
     * 根据权限id删除权限
     * @param permissionId
     */
    void deleteById(String permissionId);
}
