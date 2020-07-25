package com.itheima.service;

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
}
