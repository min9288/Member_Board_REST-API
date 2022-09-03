package com.board.domain.shopping.product.dto.requestDTO;

import com.board.domain.shopping.product.entity.enumPackage.Category;
import com.board.domain.shopping.product.entity.enumPackage.Vander;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductAddRequestDTO {

    private Category category;
    private String name;
    private int price;
    private int quantity;
    private double pointRate;
    private Vander vander;

}
