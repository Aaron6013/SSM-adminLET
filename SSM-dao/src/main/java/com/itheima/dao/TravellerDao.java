package com.itheima.dao;

import com.itheima.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/25 - 10:53
 * 旅客持久层
 */
public interface TravellerDao {

    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{id})")
    List<Traveller> findByOrderId(String id);
}
