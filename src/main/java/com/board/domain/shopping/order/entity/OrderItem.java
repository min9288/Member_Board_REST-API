package com.board.domain.shopping.order.entity;

import com.board.domain.member.entity.Member;
import com.board.domain.shopping.cart.entity.Cart;
import com.board.domain.shopping.order.entity.enumPackage.OrderMethod;
import com.board.domain.shopping.product.entity.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
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

    // order 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "order_uuid")
    private Order order;

    // product 매핑
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_uuid")
    private Product product;

    // 주문 수량
    @Column(nullable = false, name = "order_count" )
    private int orderCount;

    // 주문 상품 가격
    @Column(nullable = false, name = "order_price")
    private int orderPrice;

    @Column(nullable = false, name="order_method")
    private OrderMethod orderMethod;

    // 주문 상품 가격
    @Column(nullable = false, name = "order_total_price")
    private int orderTotalPrice;

    // 구매자
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uuid")
    private Member member;

    // 주문일
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(nullable = false, updatable = false, name = "order_date")
    private LocalDate orderDate;


    @Builder
    public OrderItem(Order order, Product product, int orderCount, int orderPrice, int orderTotalPrice, OrderMethod orderMethod, Member member) {
        this.order = order;
        this.product = product;
        this.orderCount = orderCount;
        this.orderPrice = orderPrice;
        this.orderTotalPrice = orderTotalPrice;
        this.orderMethod = orderMethod;
        this.member = member;
    }

//    // 장바구니 매핑
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "cart_uuid")
//    private Cart cart;

}
