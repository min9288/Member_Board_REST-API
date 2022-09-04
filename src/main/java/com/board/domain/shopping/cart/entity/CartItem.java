package com.board.domain.shopping.cart.entity;

import com.board.domain.member.entity.Member;
import com.board.domain.shopping.product.entity.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "cart_items")
public class CartItem {

    // 장바구니의 서브테이블로, 상바구니 안에 상품들이 모여있는 객체입니다.
    // cartItem pk
    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", name = "cart_item_uuid")
    private UUID cartItemUUID;

    // 장바구니 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "cart_uuid")
    private Cart cart;

    // 상품 매핑
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_uuid", unique = true)
    private Product product;

    // 구매수량
    @Column(name = "order_count")
    private int orderCount;

    // 장바구니 상품 등록일
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(nullable = false, updatable = false, name = "enroll_date")
    private LocalDate enrollDate;

    // 장바구니 상품 수정일
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(name = "update_date")
    private LocalDate updateDate;

    @Builder
    public CartItem(Cart cart, Product product, int orderCount) {
        this.cart = cart;
        this.product = product;
        this.orderCount = orderCount;
    }

    @Builder
    public CartItem(int orderCount) {
        this.orderCount = orderCount;
    }

    public CartItem update(CartItem cartItem) {
        this.orderCount = cartItem.orderCount;
        return this;
    }

}
