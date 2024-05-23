package com.inflearn.lecture.spring.api.service.order;

import com.inflearn.lecture.spring.api.controller.order.request.OrderCreateRequest;
import com.inflearn.lecture.spring.api.service.order.response.OrderResponse;
import com.inflearn.lecture.spring.domain.order.Order;
import com.inflearn.lecture.spring.domain.order.OrderRepository;
import com.inflearn.lecture.spring.domain.product.Product;
import com.inflearn.lecture.spring.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registedDateTime) {
        List<String> productNumbers = request.getProductNumbers();
        // Product
        List<Product> products = findProductsBy(productNumbers);

        Order order = Order.create(products, registedDateTime);
        Order saveOrder = orderRepository.save(order);
        return OrderResponse.of(saveOrder);
    }

    private List<Product> findProductsBy(List<String> productNumbers) {
        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);
        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getProductNumber, p -> p));

        return productNumbers.stream()
                .map(productMap::get)
                .collect(Collectors.toList());
    }
}
