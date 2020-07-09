package com.atxzy.ssm.service.impl;

import com.atxzy.ssm.dao.IOrdersDao;
import com.atxzy.ssm.domain.Orders;
import com.atxzy.ssm.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;




@Service
public class OrderServiceImpl implements IOrdersService {


    @Autowired
    IOrdersDao iOrdersDao;

    @Override
    public List<Orders> findAll(int page, int pageSize) throws Exception {
        //参数pageNum是页码值，pageSize是每页显示条数
        PageHelper.startPage(1,5);
        return iOrdersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) {
        return iOrdersDao.findById(ordersId);
    }

}
