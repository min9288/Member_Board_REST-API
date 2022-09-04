package com.board.domain.shopping.cart.service;

import com.board.domain.member.entity.Member;
import com.board.domain.member.repository.MemberRepository;
import com.board.domain.shopping.cart.dto.requestDTO.CartAddItemReqeustDTO;
import com.board.domain.shopping.cart.dto.requestDTO.CartUpdateItemRequestDTO;
import com.board.domain.shopping.cart.dto.responseDTO.CartGetCartItemResponseDTO;
import com.board.domain.shopping.cart.entity.Cart;
import com.board.domain.shopping.cart.entity.CartItem;
import com.board.domain.shopping.cart.repository.cart.CartRepository;
import com.board.domain.shopping.cart.repository.cartItem.CartItemRepository;
import com.board.domain.shopping.product.entity.Product;
import com.board.domain.shopping.product.repository.ProductRepository;
import com.board.exception.CustomAccessDeniedException;
import com.board.exception.MemberNotFoundException;
import com.board.exception.ProductNotFoundException;
import com.board.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;


    // 장바구니 상품추가
    @Transactional
    @Override
    public List<CartGetCartItemResponseDTO> addItemToCart(CartAddItemReqeustDTO reqeustDTO) {

        // 로그인 멤버
        Member findMember = findMember();

        // 구매 상품
        Product findProduct = productRepository.findById(reqeustDTO.getProductUUID()).orElseThrow(ProductNotFoundException::new);

        // 장바구니 가져오기 및 부재시 생성
        Cart findCart = cartRepository.findByMemberUUID(findMember.getMemberUUID());
        if(findCart == null) {
            findCart = cartRepository.save(
                    Cart.builder()
                            .member(findMember)
                            .build());
        }

        // 장바구니 상품 추가
        CartItem cartItem = cartItemRepository.save(CartItem.builder()
                        .cart(findCart)
                        .product(findProduct)
                        .orderCount(reqeustDTO.getOrderCount())
                        .build());

        // 장바구니 총 금액 변경
        findCart.setTotalPrice(findCart.getTotalPrice() + (findProduct.getPrice() * reqeustDTO.getOrderCount()));
        findCart = cartRepository.save(findCart);

        List<CartItem> cartItemList = cartItemRepository.findAllByCart(findCart);

        return cartItemList.stream()
                        .map(cartItem1 -> CartGetCartItemResponseDTO.createDTO(cartItem1))
                        .collect(Collectors.toList());
    }

    // 장바구니 상품 조회
    @Override
    public List<CartGetCartItemResponseDTO> getMyCartItem() {
        Member member = findMember();
        Cart cart = cartRepository.findByMemberUUID(member.getMemberUUID());

        List<CartItem> cartItemList = cartItemRepository.findAllByCart(cart);

        return cartItemList.stream()
                .map(cartItem -> CartGetCartItemResponseDTO.createDTO(cartItem))
                .collect(Collectors.toList());
    }

    // 장바구니 상품 수량 수정
    @Transactional
    @Override
    public CartGetCartItemResponseDTO updateCartItem(UUID cartItemUUID, CartUpdateItemRequestDTO requestDTO) {
        Member member = findMember();
        Cart cart = cartRepository.findByMemberUUID(member.getMemberUUID());
        CartItem cartItem = cartItemRepository.findByCartItemUUID(cartItemUUID).orElseThrow(ProductNotFoundException::new);

        // 현재 장바구니 총 금액
        int totalPrice = cart.getTotalPrice();

        // 수정 전 상품 금액
        int cartItemPriceBefore = cartItem.getProduct().getPrice() * cartItem.getOrderCount();

        // 장바구니 소유자와 동일한지 확인
        if(!cart.getCartUUID().equals(cartItem.getCart().getCartUUID()))
            throw new CustomAccessDeniedException();

        // 장바구니 상품 수량 수정
        CartItem updateCartItem = cartItem.update(requestDTO.toEntity());

        // 수정 후 상품 금액
        int cartItemPriceAfter = updateCartItem.getOrderCount() * updateCartItem.getProduct().getPrice();

        // 장바구니 금액 조정
        totalPrice = (totalPrice - cartItemPriceBefore) + cartItemPriceAfter;

        // 장바구니 총 금액 최신화
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);

        return CartGetCartItemResponseDTO.builder()
                .cartUUID(cartItem.getCart().getCartUUID())
                .cartItemUUID(cartItem.getCartItemUUID())
                .productName(cartItem.getProduct().getName())
                .price(cartItem.getProduct().getPrice())
                .orderCount(cartItem.getOrderCount())
                .enrollDate(cartItem.getEnrollDate())
                .updateDate(cartItem.getUpdateDate())
                .build();
    }

    // 장바구니 상품 선택 삭제
    @Transactional
    @Override
    public String deleteCartItem(UUID cartItemUUID) {
        Member member = findMember();
        Cart cart = cartRepository.findByMemberUUID(member.getMemberUUID());
        CartItem cartItem = cartItemRepository.findByCartItemUUID(cartItemUUID).orElseThrow(ProductNotFoundException::new);

        // 장바구니 소유자와 동일한지 확인
        if(!cart.getCartUUID().equals(cartItem.getCart().getCartUUID()))
            throw new CustomAccessDeniedException();

        cartItemRepository.delete(cartItem);

        return "선택 삭제 성공";

    }

    @Transactional
    @Override
    public String deleteAllCartItem() {
        Member member = findMember();
        Cart cart = cartRepository.findByMemberUUID(member.getMemberUUID());
        List<CartItem> cartItemList = cartItemRepository.findAllByCart(cart);

        for(CartItem cartItem : cartItemList) {
            cartItemRepository.delete(cartItem);
        }

        return "전체 삭제 성공";
    }


    public Member findMember() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(MemberNotFoundException::new);
    }
}
