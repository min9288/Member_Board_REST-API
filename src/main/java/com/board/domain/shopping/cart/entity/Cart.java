package com.board.domain.shopping.cart.entity;

import com.board.domain.member.entity.Member;
import com.board.domain.shopping.order.entity.Order;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    //  Order 매핑
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Order> orderList = new ArrayList<>();

    // 회원 매핑
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_uuid")
    private Member member;

    // 카트아이템 값 타입 컬렉션 매핑
    @ElementCollection
    @CollectionTable(name = "cart_item")
    @MapKeyColumn(name = "map_key")
    private Map<UUID, CartItem> cartItemMap = new HashMap<>();

}
