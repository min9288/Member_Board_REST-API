package com.board.domain.shopping.cart.repository.cartItem;

import com.board.domain.shopping.cart.entity.Cart;
import com.board.domain.shopping.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {

    List<CartItem> findAllByCart(Cart cart);

    Optional<CartItem> findByCartItemUUID(UUID cartItemUUID);
}
