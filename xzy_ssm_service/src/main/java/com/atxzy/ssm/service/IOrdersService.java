package com.atxzy.ssm.service;

import com.atxzy.ssm.domain.Orders;

import java.util.List;

public interface IOrdersService {

    List<Orders> findAll(int page, int pageSize) throws Exception;

    Orders findById(String ordersId);
}
