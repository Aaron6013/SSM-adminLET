package com.itheima.dao;

import com.itheima.domain.Order;
import com.itheima.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/25 - 10:04
 * 订单持久层
 */
public interface OrderDao {

    /**
     * 查询所有订单
     * @return
     */
    @Select("select * from orders")
    @Results(id = "orderMap",value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product",column = "productId",one = @One(select = "com.itheima.dao.ProductDao.findById")),
    })
    List<Order> findAllOrders();

    /**
     * 根据订单id查询订单
     * @param id
     * @return
     */
    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select = "com.itheima.dao.ProductDao.findById")),
            @Result(column = "memberId",property = "member",one = @One(select = "com.itheima.dao.MemberDao.findById")),
            @Result(column = "id",property = "travellers",many = @Many(select = "com.itheima.dao.TravellerDao.findByOrderId")),

    })
    Order findById(String id);
}
