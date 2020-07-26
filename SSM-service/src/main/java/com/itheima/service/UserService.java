package com.itheima.service;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/25 - 11:48
 */
public interface UserService extends UserDetailsService {

    /**
     * 查询所有用户
     * @return
     */
    List<UserInfo> findAllUser(int page, int size);

    /**
     * 保存用户
     * @param userInfo
     */
    void saveUser(UserInfo userInfo);

    /**
     * 根据用户id查询对应用户
     * @param id
     * @return
     */
    UserInfo findById(String id);

    /**
     * 根据用户id查询用户可以添加的角色
     * @param userId
     * @return
     */
    List<Role> findOtherRole(String userId);

    /**
     * 向用户添加角色
     * @param userId
     * @param roleIds
     */
    void addRoleToUser(String userId,String[] roleIds);
}
