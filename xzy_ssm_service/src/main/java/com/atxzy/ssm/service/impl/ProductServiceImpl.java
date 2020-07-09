package com.atxzy.ssm.service.impl;

import com.atxzy.ssm.dao.IProductDao;
import com.atxzy.ssm.domain.Product;
import com.atxzy.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public Product findById(String productId) throws Exception {

        return productDao.findById(productId);
    }

    @Override
    public List<Product> findAll() throws Exception {

        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}
