package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Aaron
 * @date 2020/7/25 - 9:23
 * 产品持久层
 */
public interface ProductDao {

    /**
     * 查询所有产品
     * @return
     */
    @Select("select * from product")
    List<Product> findAllProduct();

    /**
     * 保存产品
     * @param product
     */
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void saveProduct(Product product);

    /**
     * 根据i查询产品
     * @param id
     * @return
     */
    @Select("select * from product where id = #{id}")
    Product findById(String id);
}
