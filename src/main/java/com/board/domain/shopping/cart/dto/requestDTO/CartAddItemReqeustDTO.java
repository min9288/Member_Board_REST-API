package com.board.domain.shopping.cart.dto.requestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CartAddItemReqeustDTO {

    private UUID productUUID;

    @Min(1)
    private int orderCount;
}
