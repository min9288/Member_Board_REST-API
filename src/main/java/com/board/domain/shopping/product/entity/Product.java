package com.board.domain.shopping.product.entity;

import com.board.domain.shopping.order.entity.Order;
import com.board.domain.shopping.product.entity.enumPackage.Category;
import com.board.domain.shopping.product.entity.enumPackage.Vander;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "products")
public class Product {

    // 상품 PK
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", name = "product_uuid")
    private UUID productUUID;

    // 카테고리
    @Enumerated(EnumType.STRING)
    @Column
    private Category category;

    // 상품명
    @Column(nullable = false)
    private String name;

    // 가격
    @Column(nullable = false)
    private int price;

    // 재고 수량
    @Column
    private int quantity;

    // 포인트 비율
    @Column(nullable = false, name = "point_rate")
    private double pointRate;

    // 밴더사
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Vander vander;

    // 등록일
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(nullable = false, updatable = false, name = "enroll_date")
    private LocalDate enrollDate;

    // 수정일
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(name = "update_date")
    private LocalDate updateDate;


    @Builder
    public Product(Category category, String name, int price, int quantity, double pointRate, Vander vander) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.pointRate = pointRate;
        this.vander = vander;
    }

}
