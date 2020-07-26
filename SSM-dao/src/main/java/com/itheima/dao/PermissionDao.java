package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/25 - 16:48
 * 权限持久层
 */
public interface PermissionDao {

    /**
     * 根据角色id查询对应的权限
     * @param id
     * @return
     */
    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{id})")
    Permission findByRoleId(String id);

    /**
     * 查询所有权限
     * @return
     */
    @Select("select * from permission")
    List<Permission> findAllPermission();

    /**
     * 保存权限
     * @param permission
     */
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void savePermission(Permission permission);

    /**
     * 根据权限的id查询对应的权限
     * @param id
     * @return
     */
    @Select("select * from permission where id=#{id}")
    Permission findById(String id);

    /**
     * 根据权限的id删除对应的权限
     * @param id
     */
    @Delete("delete from permission where id = #{permissionId}")
    void deleteById(String permissionId);
}
