package com.board.domain.shopping.order.service;

import com.board.domain.shopping.order.dto.requestDTO.OrderByOrderItemPostResquestDTO;
import com.board.domain.shopping.order.dto.responseDTO.OrderResponseDTO;
import com.board.domain.shopping.order.entity.OrderItem;
import com.board.domain.shopping.order.entity.enumPackage.OrderMethod;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    // 일반주문
    OrderResponseDTO pushOrder(OrderByOrderItemPostResquestDTO requestDTO);

    // 장바구니 주문
    List<OrderResponseDTO> pushOrderByCart(OrderMethod orderMethod);

    // 전체 주문 내역
    List<OrderResponseDTO> getOrderList();

}
