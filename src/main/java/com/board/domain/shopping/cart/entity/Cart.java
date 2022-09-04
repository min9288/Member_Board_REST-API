package com.board.domain.shopping.cart.entity;

import com.board.domain.member.entity.Member;
import com.board.domain.shopping.order.entity.Order;
import com.board.domain.shopping.product.entity.Product;
import com.board.exception.NotEnoughStockQuantityException;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "carts")
public class Cart {

    // 장바구니 PK
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", name = "cart_uuid")
    private UUID cartUUID;

    // 회원 매핑
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uuid")
    private Member member;

    // 장바구니 아이템 총 가격
    @ColumnDefault("0")
    private int totalPrice;

    // 장바구니 아이템 테이블 매핑
    @OneToMany(fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<CartItem> cartItemList = new ArrayList<>();

    @Builder
    public Cart(Member member) {
        this.member = member;
    }

}
