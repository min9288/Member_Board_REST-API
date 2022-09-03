package com.board.domain.shopping.order.entity;

import com.board.domain.shopping.product.entity.Product;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "order_items")
public class OrderItem {

    // 주문상품 PK
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", name = "order_item_uuid")
    private UUID orderItemUUID;

    // 주문 수량
    @Column(nullable = false)
    private int count;

    // 주문 상품 가격 합계
    @Column(nullable = false, name = "total_price")
    private int totalPrice;

    // product 다대일 단방향 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_uuid")
    private Product product;

}
