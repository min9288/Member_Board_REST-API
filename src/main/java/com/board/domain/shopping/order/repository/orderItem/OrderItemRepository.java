package com.board.domain.shopping.order.repository.orderItem;

import com.board.domain.member.entity.Member;
import com.board.domain.shopping.order.entity.Order;
import com.board.domain.shopping.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID>, OrderItemCustomRepository {

    List<OrderItem> findByOrder(Order order);

    List<OrderItem> findByMember(Member member);
}
