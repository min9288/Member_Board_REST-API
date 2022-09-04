package com.board.domain.shopping.order.repository.orderItem;

import com.board.domain.shopping.order.entity.Order;
import com.board.domain.shopping.order.entity.OrderItem;

import java.util.List;

public interface OrderItemCustomRepository {

    List<OrderItem> findAllByOrder(Order order);
}
