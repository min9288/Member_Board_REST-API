package com.board.domain.shopping.order.dto.requestDTO;

import com.board.domain.shopping.order.entity.enumPackage.OrderMethod;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.util.UUID;

@Data
@NoArgsConstructor
public class OrderByOrderItemPostResquestDTO {

    private UUID productUUID;
    private OrderMethod orderMethod;

    @Min(1)
    private int orderCount;



}
