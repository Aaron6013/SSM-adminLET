package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.PermissionDao;
import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/25 - 18:27
 */
@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findAllPermission(int page, int size) {
        PageHelper.startPage(page, size);
        return permissionDao.findAllPermission();
    }

    @Override
    public void savePermission(Permission permission) {
        permissionDao.savePermission(permission);
    }

    @Override
    public Permission findById(String id) {
        return permissionDao.findById(id);
    }

    @Override
    public void deleteById(String permissionId) {
        permissionDao.deleteById(permissionId);
    }
}
