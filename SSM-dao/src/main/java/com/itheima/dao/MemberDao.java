package com.itheima.dao;

import com.itheima.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * @author Aaron
 * @date 2020/7/25 - 10:59
 * vip持久层
 */
public interface MemberDao {

    @Select("select * from member where id = #{id}")
    Member findById(String id);
}
