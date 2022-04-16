package com.example.dao.orderdao;

import com.example.pojo.Order;

/**
 * The interface Order repository.
 *
 * @author lambda
 */
public interface OrderRepository {
    /**
     * Save order.
     *
     * @param order the order
     * @return the order
     */
    Order save(Order order);
}
