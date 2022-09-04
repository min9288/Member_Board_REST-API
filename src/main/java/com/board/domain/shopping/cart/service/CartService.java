package com.board.domain.shopping.cart.service;


import com.board.domain.shopping.cart.dto.requestDTO.CartAddItemReqeustDTO;
import com.board.domain.shopping.cart.dto.requestDTO.CartUpdateItemRequestDTO;
import com.board.domain.shopping.cart.dto.responseDTO.CartGetCartItemResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CartService {

    // 장바구니 아이템 등록 (장바구니 부재시 장바구니도 등록)
    List<CartGetCartItemResponseDTO> addItemToCart(CartAddItemReqeustDTO reqeustDTO);

    // 장바구니 상품 조회
    List<CartGetCartItemResponseDTO> getMyCartItem();

    // 장바구니 상품 수량 수정
    CartGetCartItemResponseDTO updateCartItem(UUID cartItemUUID, CartUpdateItemRequestDTO requestDTO);

    // 장바구니 상품 선택 삭제
    String deleteCartItem(UUID cartItemUUID);

    // 장바구니 상품 전체 삭제
    String deleteAllCartItem();


}
