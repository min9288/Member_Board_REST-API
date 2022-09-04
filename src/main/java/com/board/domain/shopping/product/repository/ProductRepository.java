package com.board.domain.shopping.product.repository;

import com.board.domain.shopping.product.entity.Product;
import com.board.domain.shopping.product.entity.enumPackage.Category;
import com.board.domain.shopping.product.entity.enumPackage.Vander;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByCategory(Category category);

    List<Product> findByName(String name);

    List<Product> findByVander(Vander vander);


}
