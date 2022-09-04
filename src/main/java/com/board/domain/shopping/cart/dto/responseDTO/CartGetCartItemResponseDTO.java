package com.board.domain.shopping.cart.dto.responseDTO;

import com.board.domain.shopping.cart.entity.CartItem;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CartGetCartItemResponseDTO {

    private UUID cartUUID;
    private UUID cartItemUUID;
    private String productName;
    private int price;
    private int orderCount;
    private String pointRateStr;
    private LocalDate enrollDate;
    private LocalDate updateDate;



    public static CartGetCartItemResponseDTO createDTO(CartItem cartItem) {

        // double 타입 포인트적립비율 -> String 으로 변환
        double pointRateTemp = cartItem.getProduct().getPointRate() * 10;
        int pointRate = (int) pointRateTemp;
        String pointTypeConverter = String.valueOf(pointRate) + "%";

        return CartGetCartItemResponseDTO.builder()
                .cartUUID(cartItem.getCart().getCartUUID())
                .cartItemUUID(cartItem.getCartItemUUID())
                .productName(cartItem.getProduct().getName())
                .price(cartItem.getProduct().getPrice())
                .orderCount(cartItem.getOrderCount())
                .pointRateStr(pointTypeConverter)
                .enrollDate(cartItem.getEnrollDate())
                .updateDate(cartItem.getUpdateDate())
                .build();

    }


    @Builder
    public CartGetCartItemResponseDTO(UUID cartUUID, UUID cartItemUUID, String productName, int price, int orderCount,
                                      String pointRateStr, LocalDate enrollDate, LocalDate updateDate) {
        this.cartUUID = cartUUID;
        this.cartItemUUID = cartItemUUID;
        this.productName = productName;
        this.price = price;
        this.orderCount = orderCount;
        this.pointRateStr = pointRateStr;
        this.enrollDate = enrollDate;
        this.updateDate = updateDate;
    }

    @Builder
    public CartGetCartItemResponseDTO(UUID cartUUID, UUID cartItemUUID, String productName, int price, int orderCount,
                                      LocalDate enrollDate, LocalDate updateDate) {
        this.cartUUID = cartUUID;
        this.cartItemUUID = cartItemUUID;
        this.productName = productName;
        this.price = price;
        this.orderCount = orderCount;
        this.enrollDate = enrollDate;
        this.updateDate = updateDate;
    }

}
