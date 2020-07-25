package com.itheima.service;

import com.itheima.domain.Order;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/25 - 10:08
 * 订单业务层
 */
public interface OrderService {

    /**
     * 查询所有订单
     * @return
     */
    List<Order> findAllOrders(int page, int size);

    /**
     * 根据订单id查询订单
     * @param id
     * @return
     */
    Order findById(String id);
}
