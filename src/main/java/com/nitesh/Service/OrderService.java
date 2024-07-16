package com.nitesh.Service;

import com.nitesh.model.Order;
import com.nitesh.model.User;
import com.nitesh.request.OrderRequest;

import java.util.List;

public interface OrderService {

    public Order creatOrder(OrderRequest order, User user) throws Exception;

    public Order updateOrderStatus(Long orderId, String orderStatus) throws  Exception;

    public void cancelOrder(Long orderId) throws  Exception;

    public List<Order> getUserOrders(Long userId) throws  Exception;

    public List<Order> getRestaurantOrders(Long restaurantId, String orderStatus) throws  Exception;

    public Order findOrderById(Long orderId) throws  Exception;


}
