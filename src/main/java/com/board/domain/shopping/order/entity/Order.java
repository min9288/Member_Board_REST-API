package com.board.domain.shopping.order.entity;

import com.board.domain.member.entity.Member;
import com.board.domain.shopping.order.entity.enumPackage.OrderMethod;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

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

    // 주문방식 ( CASH / POINT )
    @Enumerated(EnumType.STRING)
    private OrderMethod orderMethod;

    // 주문 총 가격
    @Column(name = "total_price")
    private int totalPrice;

    // 주문자
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uuid")
    private Member member;

    // 주문일자
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(nullable = false, updatable = false, name = "order_date")
    private LocalDate orderDate;

    // 주문 상품 매핑
    @OneToMany(fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderItem> orderItemList = new ArrayList<>();

    @Builder
    public Order(OrderMethod orderMethod, int totalPrice, Member member){
        this.orderMethod = orderMethod;
        this.totalPrice = totalPrice;
        this.member = member;
    }

}
