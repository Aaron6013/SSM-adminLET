package com.itheima.service;

import com.itheima.domain.Product;

import java.util.List;
/**
 * @author Aaron
 * @date 2020/7/25 - 9:10
 * 产品业务层
 */
public interface ProductService {

    /**
     * 查询所有产品
     * @return
     */
    List<Product> findAllProduct(int page, int size);

    /**
     * 保存产品
     * @param product
     */
    void saveProduct(Product product);
}
