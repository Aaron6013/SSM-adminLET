package com.itheima.dao;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/25 - 11:54
 */
public interface UserDao {

    /**
     * 根据用户名称查询用户
     * @param username
     * @return
     */
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id",property = "roles",many = @Many(select = ("com.itheima.dao.RoleDao.findRoleByUserId")))
    })
    UserInfo findByUsername(String username);

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from users")
    List<UserInfo> findAllUser();

    @Insert("insert into users(username,email,password,phoneNum,status) values(#{username},#{email},#{password},#{phoneNum},#{status})")
    void saveUser(UserInfo userInfo);

    /**
     * 根据用户id查询相应用户
     * @param id
     * @return
     */
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id",property = "roles",many = @Many(select = ("com.itheima.dao.RoleDao.findRoleByUserId"))),
    })
    UserInfo findById(String id);

    /**
     * 查询用户可以添加的角色
     * @param id
     * @return
     */
    @Select("select * from role where id not in (select roleId from users_role where userId = #{userId})")
    List<Role> findOtherRole(String userId);

    /**
     * 向指定用户添加角色
     * @param userId
     * @param roleId
     */
    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId);
}
