package com.inflearn.lecture.spring.api.service.product;

import com.inflearn.lecture.spring.api.controller.product.dto.request.ProductCreateServiceRequest;
import com.inflearn.lecture.spring.api.service.product.response.ProductResponse;
import com.inflearn.lecture.spring.domain.product.Product;
import com.inflearn.lecture.spring.domain.product.ProductRepository;
import com.inflearn.lecture.spring.domain.product.ProductSellingStatus;
import com.inflearn.lecture.spring.domain.product.ProductType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.inflearn.lecture.spring.domain.product.ProductSellingStatus.SELLING;
import static com.inflearn.lecture.spring.domain.product.ProductType.HANDMADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@ActiveProfiles("test")
@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();
    }

    @DisplayName("상품 번호 상품 신규 상품을 등록한다")
    @Test
    void createProduct() {
        // given
        Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        productRepository.save(product1);
        ProductCreateServiceRequest request = ProductCreateServiceRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("카푸치노")
                .price(5000)
                .build();

        // when
        ProductResponse productResponse = productService.createProduct(request);

        // then
        assertThat(productResponse)
                .extracting("productNumber",
                        "type",
                        "sellingStatus",
                        "name",
                        "price")
                .contains("002",
                        HANDMADE,
                        SELLING,
                        "카푸치노",
                        5000);
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(2)
                .extracting("productNumber",
                        "type",
                        "sellingStatus",
                        "name",
                        "price")
                .containsExactlyInAnyOrder(
                        tuple("002",
                                HANDMADE,
                                SELLING,
                                "카푸치노",
                                5000),
                        tuple("001",
                                HANDMADE,
                                SELLING,
                                "아메리카노",
                                4000));
    }

    @DisplayName("상품이 하나도 없는 경우 신규 상품을 등록하면 상품번호는 001이다.")
    @Test
    void createProductWhenProductIsEmpty() {
        // given
        ProductCreateServiceRequest request = ProductCreateServiceRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("카푸치노")
                .price(5000)
                .build();
        // when
        ProductResponse productResponse = productService.createProduct(request);


        // then
        assertThat(productResponse)
                .extracting("productNumber",
                        "type",
                        "sellingType",
                        "name",
                        "price")
                .contains("001",
                        HANDMADE,
                        SELLING,
                        "카푸치노",
                        5000);
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(1)
                .extracting("productNumber",
                        "type",
                        "sellingType",
                        "name",
                        "price")
                .contains(
                        tuple("001",
                                HANDMADE,
                                SELLING,
                                "카푸치노",
                                5000));
    }

    private Product createProduct(String productNumber,
                                  ProductType type,
                                  ProductSellingStatus sellingStatus,
                                  String name,
                                  int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .sellingStatus(sellingStatus)
                .name(name)
                .price(price)
                .build();
    }
}