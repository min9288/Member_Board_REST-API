package com.board.domain.shopping.cart.repository.cart;

import com.board.domain.shopping.cart.entity.Cart;

import java.util.List;
import java.util.UUID;

public interface CartCustomRepository {


    Cart findByMemberUUID(UUID memberUUID);

}
