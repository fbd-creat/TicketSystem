package com.study.service.impl;

import com.study.bean.Order;
import com.study.mapper.OrderMapper;
import com.study.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 朱天乐
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public boolean createOrder(Order order) {

        int insert = orderMapper.insert(order);

        return insert == 1 ? true : false;
    }

    @Override
    public List<Order> getAllOrder() {
        List<Order> orders = orderMapper.selectAll();
        return orders;
    }
}
