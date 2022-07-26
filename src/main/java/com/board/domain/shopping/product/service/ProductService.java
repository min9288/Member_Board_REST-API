package com.board.domain.shopping.product.service;

import com.board.domain.shopping.product.dto.requestDTO.ProductAddRequestDTO;
import com.board.domain.shopping.product.dto.requestDTO.ProductUpdateRequestDTO;
import com.board.domain.shopping.product.dto.responseDTO.ProductAddResponseDTO;
import com.board.domain.shopping.product.dto.responseDTO.ProductGetProductListResponseDTO;
import com.board.domain.shopping.product.dto.responseDTO.ProductUpdateResponseDTO;
import com.board.domain.shopping.product.entity.enumPackage.Category;
import com.board.domain.shopping.product.entity.enumPackage.Vander;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    // 상품 등록
    ProductAddResponseDTO addProduct(ProductAddRequestDTO requestDTO);

    // 상품 수정
    ProductUpdateResponseDTO updateProduct(UUID productUUID, ProductUpdateRequestDTO requestDTO);

    // 상품 전체 조회
    List<ProductGetProductListResponseDTO> getAll();

    // 상품 카테고리 조회
    List<ProductGetProductListResponseDTO> getProductsByCategory(Category category);

    // 상품 벤더사 조회
    List<ProductGetProductListResponseDTO> getProductsByVander(Vander vander);

    // 상품명 조회
    List<ProductGetProductListResponseDTO> getProductsByName(String name);

    // 상품 UUID로 조회
    ProductGetProductListResponseDTO getProductByProductUUID(UUID productUUID);

    // 상품 삭제
    String deleteProduct(UUID productUUID);
}
