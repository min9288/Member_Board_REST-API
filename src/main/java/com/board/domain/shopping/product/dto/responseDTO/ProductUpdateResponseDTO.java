package com.board.domain.shopping.product.dto.responseDTO;

import com.board.domain.shopping.product.entity.enumPackage.Category;
import com.board.domain.shopping.product.entity.enumPackage.Vander;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductUpdateResponseDTO {

    private Category category;
    private String name;
    private int price;
    private int quantity;
    private double pointRate;
    private Vander vander;

    @Builder
    public ProductUpdateResponseDTO(Category category, String name, int price, int quantity, double pointRate,
                                    Vander vander) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.pointRate = pointRate;
        this.vander = vander;

    }
}
