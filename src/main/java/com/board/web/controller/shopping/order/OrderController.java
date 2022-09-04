package com.board.web.controller.shopping.order;

import com.board.domain.response.service.ResponseService;
import com.board.domain.result.MultipleResult;
import com.board.domain.result.SingleResult;
import com.board.domain.shopping.order.dto.requestDTO.OrderByOrderItemPostResquestDTO;
import com.board.domain.shopping.order.dto.responseDTO.OrderResponseDTO;
import com.board.domain.shopping.order.entity.enumPackage.OrderMethod;
import com.board.domain.shopping.order.service.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final ResponseService responseService;
    private final OrderServiceImpl orderService;

    // 일반 주문
    @PostMapping("/push-order")
    public SingleResult<OrderResponseDTO> pushOrder(@RequestBody @Valid OrderByOrderItemPostResquestDTO resquestDTO) {
        return responseService.getSingleResult(orderService.pushOrder(resquestDTO));
    }

    // 장바구니 주문
    @PostMapping("/push-order-by-cart/order-method/{orderMethod}")
    public MultipleResult<OrderResponseDTO> pushOrderByCart(@PathVariable OrderMethod orderMethod) {
        return responseService.getMultipleResult(orderService.pushOrderByCart(orderMethod));
    }

    // 전체 주문 내역
    @GetMapping
    public MultipleResult<OrderResponseDTO> getOrderList() {
        return responseService.getMultipleResult(orderService.getOrderList());
    }

}
