package com.board.domain.shopping.order.service;

import com.board.domain.member.entity.Member;
import com.board.domain.member.repository.MemberRepository;
import com.board.domain.shopping.cart.entity.Cart;
import com.board.domain.shopping.cart.entity.CartItem;
import com.board.domain.shopping.cart.repository.cart.CartRepository;
import com.board.domain.shopping.cart.repository.cartItem.CartItemRepository;
import com.board.domain.shopping.order.dto.requestDTO.OrderByOrderItemPostResquestDTO;
import com.board.domain.shopping.order.dto.responseDTO.OrderResponseDTO;
import com.board.domain.shopping.order.entity.Order;
import com.board.domain.shopping.order.entity.OrderItem;
import com.board.domain.shopping.order.entity.enumPackage.OrderMethod;
import com.board.domain.shopping.order.repository.order.OrderRepository;
import com.board.domain.shopping.order.repository.orderItem.OrderItemCustomRepositoryImpl;
import com.board.domain.shopping.order.repository.orderItem.OrderItemRepository;
import com.board.domain.shopping.product.entity.Product;
import com.board.domain.shopping.product.repository.ProductRepository;
import com.board.exception.*;
import com.board.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemCustomRepositoryImpl orderItemCustomRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    // 일반주문
    @Transactional
    @Override
    public OrderResponseDTO pushOrder(OrderByOrderItemPostResquestDTO requestDTO) {

        Member member = findMember();
        Product product = productRepository.findById(requestDTO.getProductUUID()).orElseThrow(ProductNotFoundException::new);

        if(product.getQuantity() < requestDTO.getOrderCount())
            throw new NotEnoughStockQuantityException();

        // 회원 보유 금액
        int money = member.getMoney();
        // 회원 보유 포인트
        int point = member.getPoint();
        // 결제 예정 금액
        int totalPrice = product.getPrice() * requestDTO.getOrderCount();
        // 적립 포인트
        int savingPoint = 0;

        // 현금 결재
        if(requestDTO.getOrderMethod() == OrderMethod.CASH) {
            if (money >= totalPrice) {

                savingPoint = (int) (totalPrice * product.getPointRate());

                // 결제로 인한 자금 차감 및 포인트 적립
                member.setPoint(member.getPoint() + savingPoint);
                member.setMoney(money - totalPrice);
                memberRepository.save(member);

                // 결제로 인한 상품 수량 변동
                product.setQuantity(product.getQuantity() - requestDTO.getOrderCount());
                productRepository.save(product);

            } else {
                throw new AssetsNotEnoughException();
            }
        }

        // 포인트 결재
        else if (requestDTO.getOrderMethod() == OrderMethod.POINT) {
            if (point >= totalPrice) {

                // 결제로 인한 포인트 차감 (포인트 결제는 포인트 적립 안됨)
                member.setPoint(point - totalPrice);
                memberRepository.save(member);

                // 결제로 인한 상품 수량 변동
                product.setQuantity(product.getQuantity() - requestDTO.getOrderCount());
                productRepository.save(product);

            } else {
                throw new AssetsNotEnoughException();
            }
        }

        // 주문 등록
        Order order = orderRepository.save(Order.builder()
                        .orderMethod(requestDTO.getOrderMethod())
                        .totalPrice(totalPrice)
                        .member(member)
                        .build());

        // 주문 아이템 등록
        OrderItem orderItem = orderItemRepository.save(OrderItem.builder()
                        .order(order)
                        .product(product)
                        .orderCount(requestDTO.getOrderCount())
                        .orderPrice(totalPrice)
                        .orderMethod(requestDTO.getOrderMethod())
                        .orderTotalPrice(totalPrice)
                        .member(member)
                        .build());

        return OrderResponseDTO.builder()
                .orderUUID(order.getOrderUUID())
                .orderItemUUID(orderItem.getOrderItemUUID())
                .productName(product.getName())
                .orderPrice(orderItem.getOrderPrice())
                .orderTotalPrice(totalPrice)
                .orderMethod(requestDTO.getOrderMethod())
                .orderDate(order.getOrderDate().now())
                .build();
    }

    // 장바구니 주문
    @Transactional
    @Override
    public List<OrderResponseDTO> pushOrderByCart(OrderMethod orderMethod) {

        Member member = findMember();
        Cart cart = cartRepository.findByMemberUUID(member.getMemberUUID());
        List<CartItem> cartItemList = cartItemRepository.findAllByCart(cart);

        // 회원 보유 금액
        int money = member.getMoney();
        // 회원 보유 포인트
        int point = member.getPoint();
        // 결제 예정 금액
        int totalPrice = cart.getTotalPrice();
        // 적립 포인트
        int savingPoint = 0;
        // 결제 포인트



        // 주문 등록
        Order order = orderRepository.save(Order.builder()
                .orderMethod(orderMethod)
                .totalPrice(totalPrice)
                .member(member)
                .build());

        // 장바구니 아이템 만큼 결제 프로세스 진행
        for(CartItem cartItem : cartItemList) {

            Product product = productRepository.findById(cartItem.getProduct().getProductUUID()).orElseThrow(ProductNotFoundException::new);

            if (product.getQuantity() < cartItem.getOrderCount())
                throw new NotEnoughStockQuantityException();


            // 현금 결재
            if (orderMethod == OrderMethod.CASH) {
                if (money >= totalPrice) {


                    savingPoint += (int) (totalPrice * product.getPointRate());

                    // 주문 아이템 등록
                    OrderItem orderItem = orderItemRepository.save(OrderItem.builder()
                            .order(order)
                            .product(product)
                            .orderCount(cartItem.getOrderCount())
                            .orderPrice(cartItem.getProduct().getPrice() * cartItem.getOrderCount())
                            .orderMethod(orderMethod)
                            .orderTotalPrice(totalPrice)
                            .member(member)
                            .build());

                    // 결제로 인한 상품 수량 변동
                    product.setQuantity(product.getQuantity() - cartItem.getOrderCount());
                    productRepository.save(product);

                    // 결제 후 장바구니 상품들 삭제
                    cartItemRepository.delete(cartItem);




                } else {
                    throw new AssetsNotEnoughException();
                }
            }

            // 포인트 결재
            else if (orderMethod == OrderMethod.POINT) {
                if (point >= totalPrice) {

                    // 주문 아이템 등록
                    OrderItem orderItem = orderItemRepository.save(OrderItem.builder()
                            .order(order)
                            .product(product)
                            .orderCount(cartItem.getOrderCount())
                            .orderPrice(cartItem.getProduct().getPrice() * cartItem.getOrderCount())
                            .orderMethod(orderMethod)
                            .orderTotalPrice(totalPrice)
                            .member(member)
                            .build());

                    // 결제로 인한 상품 수량 변동
                    product.setQuantity(product.getQuantity() - cartItem.getOrderCount());
                    productRepository.save(product);

                    // 결제 후 장바구니 상품들 삭제
                    cartItemRepository.delete(cartItem);

                } else {
                    throw new AssetsNotEnoughException();
                }
            }

        }

        if (orderMethod == OrderMethod.CASH) {
            // 결제로 인한 자금 차감 및 포인트 적립
            member.setPoint(member.getPoint() + savingPoint);
            member.setMoney(money - totalPrice);
            memberRepository.save(member);
        }
        else if (orderMethod == OrderMethod.POINT) {
            // 결제로 인한 포인트 차감 (포인트 결제는 포인트 적립 안됨)
            member.setPoint(point - totalPrice);
            memberRepository.save(member);
        }

        // 장바구니 총 금액 합 초기화
        cart.setTotalPrice(0);
        cartRepository.save(cart);


        List<OrderItem> orderItemList = orderItemRepository.findByOrder(order);

        return orderItemList.stream()
                .map(orderItem -> OrderResponseDTO.createDTO(orderItem))
                .collect(Collectors.toList());
    }

    // 주문 내역
    @Override
    public List<OrderResponseDTO> getOrderList() {
        Member member = findMember();

        List<OrderItem> orderItemList = orderItemRepository.findByMember(member);

        return orderItemList.stream()
                .map(orderItem -> OrderResponseDTO.createDTO(orderItem))
                .collect(Collectors.toList());
    }


    public Member findMember() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(MemberNotFoundException::new);
    }
}
