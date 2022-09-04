package com.board.domain.shopping.cart.dto.requestDTO;

import com.board.domain.shopping.cart.entity.CartItem;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartUpdateItemRequestDTO {

    private int orderCount;

    public CartItem toEntity(){
        return new CartItem(orderCount);
    }
}
