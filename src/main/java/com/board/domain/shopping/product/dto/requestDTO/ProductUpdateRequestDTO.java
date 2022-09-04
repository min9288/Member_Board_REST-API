package com.board.domain.shopping.product.dto.requestDTO;

import com.board.domain.shopping.product.entity.Product;
import com.board.domain.shopping.product.entity.enumPackage.Category;
import com.board.domain.shopping.product.entity.enumPackage.Vander;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductUpdateRequestDTO {

    private Category category;
    private String name;
    private int price;
    private int quantity;
    private double pointRate;
    private Vander vander;

    public Product toEntity() {
        return new Product(category, name, price, quantity, pointRate, vander);
    }
}
