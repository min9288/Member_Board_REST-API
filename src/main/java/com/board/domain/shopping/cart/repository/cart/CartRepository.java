package com.board.domain.shopping.cart.repository.cart;

import com.board.domain.shopping.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID>, CartCustomRepository {

//    List<Cart> findListByMemberUUID(UUID memberUUID);


}
