package com.inflearn.lecture.spring.api.service.product;

import com.inflearn.lecture.spring.api.controller.product.dto.request.ProductCreateRequest;
import com.inflearn.lecture.spring.api.controller.product.dto.request.ProductCreateServiceRequest;
import com.inflearn.lecture.spring.api.service.product.response.ProductResponse;
import com.inflearn.lecture.spring.domain.product.Product;
import com.inflearn.lecture.spring.domain.product.ProductRepository;
import com.inflearn.lecture.spring.domain.product.ProductSellingStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    // 동시성 이슈
    // uuid
    @Transactional
    public ProductResponse createProduct(ProductCreateServiceRequest request) {
        String nextProductNumber = createNextProductNumber();

        Product product = request.toEntity(nextProductNumber);
        Product savedProduct = productRepository.save(product);

        return ProductResponse.of(savedProduct);
    }


    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());
        return products.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    private String createNextProductNumber() {
        String latestProductNumber = productRepository.findLatestProductNumber();
        if (latestProductNumber == null) {
            return "001";
        }
        Integer latestProductNumberInt = Integer.parseInt(latestProductNumber) + 1;
        return String.format("%03d", latestProductNumberInt);
    }
}
