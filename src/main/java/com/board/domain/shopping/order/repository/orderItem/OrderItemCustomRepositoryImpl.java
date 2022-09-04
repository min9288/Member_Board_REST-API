package com.board.domain.shopping.order.repository.orderItem;

import com.board.domain.shopping.order.entity.Order;
import com.board.domain.shopping.order.entity.OrderItem;
import com.board.domain.shopping.order.entity.QOrder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.board.domain.shopping.order.entity.QOrderItem.orderItem;

public class OrderItemCustomRepositoryImpl implements OrderItemCustomRepository{

    JPAQueryFactory jpaQueryFactory;

    public OrderItemCustomRepositoryImpl(EntityManager em) {
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<OrderItem> findAllByOrder(Order order) {

        List<OrderItem> orderItemList = jpaQueryFactory.selectFrom(orderItem)
                .where(orderItem.order.eq(order))
                .fetch();
        return orderItemList;
    }
}
