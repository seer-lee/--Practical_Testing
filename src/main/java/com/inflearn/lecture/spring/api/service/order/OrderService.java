package com.inflearn.lecture.spring.api.service.order;

import com.inflearn.lecture.spring.api.controller.order.request.OrderCreateRequest;
import com.inflearn.lecture.spring.api.service.order.response.OrderResponse;
import com.inflearn.lecture.spring.domain.order.Order;
import com.inflearn.lecture.spring.domain.order.OrderRepository;
import com.inflearn.lecture.spring.domain.product.Product;
import com.inflearn.lecture.spring.domain.product.ProductType;
import com.inflearn.lecture.spring.domain.product.ProductRepository;
import com.inflearn.lecture.spring.domain.stock.Stock;
import com.inflearn.lecture.spring.domain.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final StockRepository stockRepository;
    public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registedDateTime) {
        List<String> productNumbers = request.getProductNumbers();
        List<Product> products = findProductsBy(productNumbers);

        // 재고 차감 체크가 필요한 상품들 필터링 한다

        List<String> stockProductNumbers = products.stream()
                .filter(product -> ProductType.containsStockType(product.getType()))
                .map(Product::getProductNumber)
                .collect(Collectors.toList());
        // 재고 엔티티 조회
        List<Stock> stocks = stockRepository.findAllByProductNumberIn(stockProductNumbers);
        Map<String, Stock> stockMap = stocks.stream()
                .collect(Collectors.toMap(Stock::getProductNumber, s->s));

        // 상품별 counting
        Map<String, Long> productCountingMap = stockProductNumbers.stream()
                .collect(Collectors.groupingBy(p->p, Collectors.counting()));
        // 재고 차감 시도
        for (String stockProductNumber : new HashSet<>(stockProductNumbers)) {
            Stock stock = stockMap.get(stockProductNumber);
            int quantity = productCountingMap.get(stockProductNumber).intValue();
            if (stock.isQuantityLessThan(quantity)) {
                throw new IllegalArgumentException("재고가 부족한 상품이 있습니다");
            }
            stock.deductQuantity(quantity);
        }

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
