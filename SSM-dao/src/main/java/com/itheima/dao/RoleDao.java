package com.itheima.dao;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;

import javax.swing.*;
import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/25 - 12:30
 * 角色持久层
 */
public interface RoleDao {

    /**
     * 根据用户id查询对应的角色
     * @param id
     * @return
     */
    @Select("select * from role where id in (select roleId from users_role where userId = #{id})")
    @Results(id = "roleMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",many = @Many(
                    select = ("com.itheima.dao.PermissionDao.findByRoleId")
            ))
    })
    List<Role> findRoleByUserId(String id);

    /**
     * 查询所有角色
     * @return
     */
    @Select("select * from role")
    List<Role> findAllRole();

    /**
     * 添加角色
     */
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void addRole(Role role);

    /**
     * 根据角色id查询对应角色
     * @param id
     * @return
     */
    @Select("select * from role where id = #{id}")
    @Results(id = "roleMap1",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",many = @Many(
                    select = ("com.itheima.dao.PermissionDao.findByRoleId")
            ))
    })
    Role findById(String id);

    /**
     * 根据角色id删除对应角色
     * @param roleId
     */
    @Delete("delete from role where id = #{roleId}")
    void deleteById(String roleId);

    /**
     * 根据角色id查询可以添加的权限
     * @param roleId
     * @return
     */
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermission(String roleId);

    /**
     * 向角色添加权限
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId);
}
