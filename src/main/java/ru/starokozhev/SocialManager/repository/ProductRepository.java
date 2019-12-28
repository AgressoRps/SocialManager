package ru.starokozhev.SocialManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.starokozhev.SocialManager.entity.Product;
import ru.starokozhev.SocialManager.enums.ProductName;
import ru.starokozhev.SocialManager.enums.ProductType;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {

    List<Product> findProductByName(ProductName name);

    List<Product> findProductByType(ProductType type);

    Product findProductById(Long id);

}
