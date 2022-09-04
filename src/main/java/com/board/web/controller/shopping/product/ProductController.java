package com.board.web.controller.shopping.product;

import com.board.domain.response.service.ResponseService;
import com.board.domain.result.MultipleResult;
import com.board.domain.result.SingleResult;
import com.board.domain.shopping.product.dto.requestDTO.ProductAddRequestDTO;
import com.board.domain.shopping.product.dto.requestDTO.ProductUpdateRequestDTO;
import com.board.domain.shopping.product.dto.responseDTO.ProductAddResponseDTO;
import com.board.domain.shopping.product.dto.responseDTO.ProductGetProductListResponseDTO;
import com.board.domain.shopping.product.dto.responseDTO.ProductUpdateResponseDTO;
import com.board.domain.shopping.product.entity.enumPackage.Category;
import com.board.domain.shopping.product.entity.enumPackage.Vander;
import com.board.domain.shopping.product.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ResponseService responseService;
    private final ProductServiceImpl productService;

    // 상품 등록
    @PostMapping
    public SingleResult<ProductAddResponseDTO> addProduct(@RequestBody @Valid ProductAddRequestDTO requestDTO) {
        return responseService.getSingleResult(productService.addProduct(requestDTO));
    }

    // 상품 수정
    @PutMapping("/{productUUID}")
    public SingleResult<ProductUpdateResponseDTO> updateProduct(@PathVariable("productUUID") UUID productUUID,
                                                                @RequestBody @Valid ProductUpdateRequestDTO requestDTO) {
        return responseService.getSingleResult(productService.updateProduct(productUUID, requestDTO));
    }
    // 상품 전체 조회
    @GetMapping()
    public MultipleResult<ProductGetProductListResponseDTO> getAll() {
        return responseService.getMultipleResult(productService.getAll());
    }

    // 상품 카테고리별 조회
    @GetMapping("/category/{category}")
    public MultipleResult<ProductGetProductListResponseDTO> getProductsByCategory(@PathVariable("category") Category category) {
        return responseService.getMultipleResult(productService.getProductsByCategory(category));
    }

    // 상품 벤더사별 조회
    @GetMapping("/vander/{vander}")
    public MultipleResult<ProductGetProductListResponseDTO> getProductsByVander(@PathVariable("vander") Vander vander) {
        return responseService.getMultipleResult(productService.getProductsByVander(vander));
    }

    // 상품명별 조회
    @GetMapping("/product-name/{productName}")
    public MultipleResult<ProductGetProductListResponseDTO> getProductsByName(@PathVariable("productName") String productName) {
        return responseService.getMultipleResult(productService.getProductsByName(productName));
    }

    // 상품 UUID로 조회
    @GetMapping("/product-uuid/{productUUID}")
    public SingleResult<ProductGetProductListResponseDTO> getProductByProductUUID(@PathVariable("productUUID") UUID productUUID) {
        return responseService.getSingleResult(productService.getProductByProductUUID(productUUID));
    }

    // 상품 삭제
    @DeleteMapping("/{productUUID}")
    public String deleteProduct(@PathVariable("productUUID") UUID productUUID) {
        return productService.deleteProduct(productUUID);
    }
}
