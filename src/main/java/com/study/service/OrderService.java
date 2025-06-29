package com.study.service;

import com.study.bean.Order;

import java.util.List;

/**
 * @author 朱天乐
 */
public interface OrderService {

    boolean createOrder(Order order);

    List<Order> getAllOrder();

}
