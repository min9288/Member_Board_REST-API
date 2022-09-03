package com.board.domain.shopping.cart.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Getter
public class CartItem {

    // cart UUID
    @Column(columnDefinition = "BINARY(16)", name = "cart_uuid")
    private UUID cartUUID;

    // product UUID
    @Column(columnDefinition = "BINARY(16)", name = "item_uuid")
    private UUID itemUUID;

    // 구매수량
    @Column(name = "order_count")
    private Integer orderCount;

    public CartItem(UUID cartUUID, UUID itemUUID, Integer orderCount) {
        this.cartUUID = cartUUID;
        this.itemUUID = itemUUID;
        this.orderCount = orderCount;
    }

}
