package com.board.domain.shopping.order.repository.order;

import com.board.domain.member.entity.Member;
import com.board.domain.shopping.order.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    Optional<Order> findByMember(Member member);
}
