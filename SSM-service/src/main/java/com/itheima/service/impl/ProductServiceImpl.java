package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/25 - 9:27
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAllProduct(int page, int size) {
        PageHelper.startPage(page,size);
        return productDao.findAllProduct();
    }

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    public void saveProduct(Product product) {
        productDao.saveProduct(product);
    }
}
