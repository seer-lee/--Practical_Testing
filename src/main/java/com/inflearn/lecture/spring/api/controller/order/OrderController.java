package com.inflearn.lecture.spring.api.controller.order;

import com.inflearn.lecture.spring.api.controller.order.request.OrderCreateRequest;
import com.inflearn.lecture.spring.api.service.order.OrderService;
import com.inflearn.lecture.spring.api.service.order.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/api/v1/orders")
    public OrderResponse createOrder(@RequestBody OrderCreateRequest request) {
        LocalDateTime registedDateTime = LocalDateTime.now();
        return orderService.createOrder(request, registedDateTime);
    }
}
