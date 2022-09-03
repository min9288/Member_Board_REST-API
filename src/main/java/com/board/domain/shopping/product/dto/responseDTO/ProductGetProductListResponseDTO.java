package com.board.domain.shopping.product.dto.responseDTO;

import com.board.domain.shopping.product.entity.Product;
import com.board.domain.shopping.product.entity.enumPackage.Category;
import com.board.domain.shopping.product.entity.enumPackage.Vander;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ProductGetProductListResponseDTO {

    private UUID productUUID;
    private Category category;
    private String name;
    private int price;
    private int quantity;
    private double pointRate;
    private Vander vander;
    private LocalDate enrollDate;
    private LocalDate updateDate;

    public static ProductGetProductListResponseDTO createDTO(Product product) {
        return ProductGetProductListResponseDTO.builder()
                .productUUID(product.getProductUUID())
                .category(product.getCategory())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .pointRate(product.getPointRate())
                .vander(product.getVander())
                .enrollDate(product.getEnrollDate())
                .updateDate(product.getUpdateDate())
                .build();
    }

    @Builder
    public ProductGetProductListResponseDTO(UUID productUUID, Category category, String name, int price, int quantity,
                                            double pointRate, Vander vander, LocalDate enrollDate, LocalDate updateDate){
        this.productUUID = productUUID;
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.pointRate = pointRate;
        this.vander = vander;
        this.enrollDate = enrollDate;
        this.updateDate = updateDate;
    }

}
