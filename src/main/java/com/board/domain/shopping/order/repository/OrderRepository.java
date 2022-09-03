package com.board.domain.shopping.order.repository;

import com.board.domain.shopping.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID>, OrderCustomRepository {
}
