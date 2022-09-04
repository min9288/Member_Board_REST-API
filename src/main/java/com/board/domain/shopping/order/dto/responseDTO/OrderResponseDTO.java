package com.board.domain.shopping.order.dto.responseDTO;

import com.board.domain.member.entity.Member;
import com.board.domain.shopping.order.entity.Order;
import com.board.domain.shopping.order.entity.OrderItem;
import com.board.domain.shopping.order.entity.enumPackage.OrderMethod;
import com.board.domain.shopping.product.entity.Product;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class OrderResponseDTO {

    private UUID orderUUID;
    private UUID orderItemUUID;
    private String productName;
    private int orderPrice;
    private int orderTotalPrice;
    private OrderMethod orderMethod;
    private LocalDate orderDate;

    public static OrderResponseDTO createDTO(OrderItem orderItem) {
        return OrderResponseDTO.builder()
                .orderUUID(orderItem.getOrder().getOrderUUID())
                .orderItemUUID(orderItem.getOrderItemUUID())
                .productName(orderItem.getProduct().getName())
                .orderPrice(orderItem.getOrderPrice())
                .orderTotalPrice(orderItem.getOrderTotalPrice())
                .orderMethod(orderItem.getOrderMethod())
                .orderDate(orderItem.getOrder().getOrderDate())
                .build();
    }

    @Builder
    public OrderResponseDTO(UUID orderUUID, UUID orderItemUUID, String productName, int orderPrice,
                            int orderTotalPrice, OrderMethod orderMethod, LocalDate orderDate) {
        this.orderUUID = orderUUID;
        this.orderItemUUID = orderItemUUID;
        this.productName = productName;
        this.orderPrice = orderPrice;
        this.orderTotalPrice = orderTotalPrice;
        this.orderMethod = orderMethod;
        this.orderDate = orderDate;
    }

    @Builder
    public OrderResponseDTO(UUID orderUUID, UUID orderItemUUID, String productName,
                            int orderTotalPrice, OrderMethod orderMethod, LocalDate orderDate) {
        this.orderUUID = orderUUID;
        this.orderItemUUID = orderItemUUID;
        this.productName = productName;
        this.orderTotalPrice = orderTotalPrice;
        this.orderMethod = orderMethod;
        this.orderDate = orderDate;
    }

}
