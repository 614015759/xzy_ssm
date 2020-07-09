package com.atxzy.ssm.service;

import com.atxzy.ssm.domain.Permission;
import com.atxzy.ssm.domain.Product;

import java.util.List;

public interface IProductService {
    /*
    根据id查询产品
     */
    public Product findById(String productId) throws Exception;

    List<Product> findAll() throws Exception;

    void save(Product product);
}
