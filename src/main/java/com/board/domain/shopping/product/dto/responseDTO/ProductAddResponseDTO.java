package com.board.domain.shopping.product.dto.responseDTO;

import com.board.domain.shopping.product.entity.enumPackage.Category;
import com.board.domain.shopping.product.entity.enumPackage.Vander;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ProductAddResponseDTO {

    private UUID productUUID;
    private Category category;
    private String name;
    private int price;
    private int quantity;
    private double pointRate;
    private Vander vander;
    private LocalDate enrollDate;

    @Builder
    public ProductAddResponseDTO(UUID productUUID, Category category, String name, int price, int quantity,
                                 double pointRate, Vander vander, LocalDate enrollDate){
        this.productUUID = productUUID;
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.pointRate = pointRate;
        this.vander = vander;
        this.enrollDate = enrollDate;
    }

}
