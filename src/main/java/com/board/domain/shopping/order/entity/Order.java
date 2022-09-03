package com.board.domain.shopping.order.entity;

import com.board.domain.member.entity.Member;
import com.board.domain.shopping.cart.entity.Cart;
import com.board.domain.shopping.order.entity.enumPackage.OrderStatus;
import com.board.domain.shopping.product.entity.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "orders")
public class Order {

    // 주문 PK
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", name = "order_uuid")
    private UUID orderUUID;

    // 주문 총 가격
    @Column(name = "total_price")
    private int totalPrice;

    // 취소여부
    @Column(name = "cancel_order")
    private Boolean cancelOrder;

    // 주문일자
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(nullable = false, updatable = false, name = "order_date")
    private LocalDate orderDate;

    // 취소일자
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(name = "order_cancel_date")
    private LocalDate orderCancelDate;

    // 구매자
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uuid")
    private Member member;

    // 주문 상품들
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_item_uuid")
    private List<OrderItem> orderItemList = new ArrayList<>();



}
