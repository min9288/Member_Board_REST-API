package com.board.domain.shopping.product.service;

import com.board.domain.board.dto.responseDTO.BoardGetBoardListResponseDTO;
import com.board.domain.shopping.product.dto.requestDTO.ProductAddRequestDTO;
import com.board.domain.shopping.product.dto.responseDTO.ProductAddResponseDTO;
import com.board.domain.shopping.product.dto.responseDTO.ProductGetProductListResponseDTO;
import com.board.domain.shopping.product.entity.Product;
import com.board.domain.shopping.product.entity.enumPackage.Category;
import com.board.domain.shopping.product.entity.enumPackage.Vander;
import com.board.domain.shopping.product.repository.ProductCustomRepositoryImpl;
import com.board.domain.shopping.product.repository.ProductRepository;
import com.board.exception.MemberNotFoundException;
import com.board.exception.ProcessFailureException;
import com.board.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductCustomRepositoryImpl productCustomRepository;

    // 상품 등록
    @Transactional
    @Override
    public ProductAddResponseDTO addProduct(ProductAddRequestDTO requestDTO) {

        Product product = productRepository.save(Product.builder()
                .category(requestDTO.getCategory())
                .name(requestDTO.getName())
                .price(requestDTO.getPrice())
                .quantity(requestDTO.getQuantity())
                .pointRate(requestDTO.getPointRate())
                .vander(requestDTO.getVander())
                .build());

        return ProductAddResponseDTO.builder()
                .productUUID(product.getProductUUID())
                .category(product.getCategory())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .pointRate(product.getPointRate())
                .vander(product.getVander())
                .enrollDate(product.getEnrollDate())
                .build();
    }

    // 상품 전체조회
    @Override
    public List<ProductGetProductListResponseDTO> getAll() {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .map(product -> ProductGetProductListResponseDTO.createDTO(product))
                .collect(Collectors.toList());
    }

    // 상품 카테고리로 조회
    @Override
    public List<ProductGetProductListResponseDTO> getProductCategory(Category category) {
        List<Product> productList = new ArrayList<>();
        try {
            productList = productRepository.findByCategory(category);
        } catch (Exception e) {
            throw new ProductNotFoundException();
        }
        return productList.stream()
                .map(product -> ProductGetProductListResponseDTO.createDTO(product))
                .collect(Collectors.toList());
    }

    // 상품 벤더사로 조회
    @Override
    public List<ProductGetProductListResponseDTO> getProductVander(Vander vander) {
        List<Product> productList = new ArrayList<>();
        try {
            productList = productRepository.findByVander(vander);
        } catch (Exception e) {
            throw new ProductNotFoundException();
        }
        return productList.stream()
                .map(product -> ProductGetProductListResponseDTO.createDTO(product))
                .collect(Collectors.toList());
    }

    // 상품 이름으로 조회
    @Override
    public List<ProductGetProductListResponseDTO> getProductName(String name) {
        List<Product> productList = new ArrayList<>();
        try {
            productList = productRepository.findByName(name);
        } catch (Exception e) {
            throw new ProductNotFoundException();
        }
        return productList.stream()
                .map(product -> ProductGetProductListResponseDTO.createDTO(product))
                .collect(Collectors.toList());
    }

    // 상품 pk로 조회
    @Override
    public List<ProductGetProductListResponseDTO> getProductUUID(UUID productUUID) {
        List<Product> productList = new ArrayList<>();
        try {
            productList = productRepository.findByProductUUID(productUUID);
        } catch (Exception e) {
            throw new ProductNotFoundException();
        }
        return productList.stream()
                .map(product -> ProductGetProductListResponseDTO.createDTO(product))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public String deleteProduct(UUID productUUID) {
        Product product = productRepository.findById(productUUID).orElseThrow(ProductNotFoundException::new);
        try {
            productRepository.delete(product);
        } catch (Exception e) {
            throw new ProcessFailureException();
        }
        return "상품을 삭제했습니다.";
    }
}
