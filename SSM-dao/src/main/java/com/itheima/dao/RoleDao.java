package com.itheima.dao;

import com.itheima.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/25 - 12:30
 */
public interface RoleDao {

    /**
     * 根据用户id查询对应的角色
     * @param id
     * @return
     */
    @Select("select * from role where id in (select roleId from users_role where userId = #{id})")
    List<Role> findRoleByUserId(String id);
}
