package com.board.domain.shopping.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    @Column(columnDefinition = "BINARY(16)")
    private UUID productUUID;

    // 상품명

    // 가격

    // 재고 수량

    // 포인트 비율

    // 밴더사

    // 등록일

    // 수정일

    // 장바구니 매핑


}
