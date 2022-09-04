package com.board.web.controller.shopping.cart;

import com.board.domain.response.service.ResponseService;
import com.board.domain.result.MultipleResult;
import com.board.domain.result.SingleResult;
import com.board.domain.shopping.cart.dto.requestDTO.CartAddItemReqeustDTO;
import com.board.domain.shopping.cart.dto.requestDTO.CartUpdateItemRequestDTO;
import com.board.domain.shopping.cart.dto.responseDTO.CartGetCartItemResponseDTO;
import com.board.domain.shopping.cart.service.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final ResponseService responseService;
    private final CartServiceImpl cartService;

    // 장바구니에 상품 추가
    @PostMapping()
    public MultipleResult<CartGetCartItemResponseDTO> addItem(@RequestBody @Valid CartAddItemReqeustDTO reqeustDTO) {
        return responseService.getMultipleResult(cartService.addItemToCart(reqeustDTO));
    }

    // 장바구니 상품 수량 수정
    @PutMapping("/cart-item-uuid/{cartItemUUID}")
    public SingleResult<CartGetCartItemResponseDTO> updateCartItem(@PathVariable UUID cartItemUUID, @RequestBody @Valid CartUpdateItemRequestDTO reqeustDTO) {
        return responseService.getSingleResult(cartService.updateCartItem(cartItemUUID, reqeustDTO));
    }

    // 장바구니 조회
    @GetMapping()
    public MultipleResult<CartGetCartItemResponseDTO> getMyCartItem() {
        return responseService.getMultipleResult(cartService.getMyCartItem());
    }

    // 장바구니 상품 선택 삭제
    @DeleteMapping("/delete-cart-item/cart-item-uuid/{cartItemUUID}")
    public String deleteCartItem(@PathVariable UUID cartItemUUID) {
        return cartService.deleteCartItem(cartItemUUID);
    }

    // 장바구니 상품 전체 삭제
    @DeleteMapping("/delete-all-cart-item")
    public String deleteAllCartItem() {
        return cartService.deleteAllCartItem();
    }
}
