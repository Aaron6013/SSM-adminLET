package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.OrderDao;
import com.itheima.domain.Order;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/25 - 10:08
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> findAllOrders(int page, int size) {
        PageHelper.startPage(page,size); //page为哪一页，size为一页大小
        return orderDao.findAllOrders();
    }

    @Override
    public Order findById(String id) {
        return orderDao.findById(id);
    }
}
