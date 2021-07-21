package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.OrderRegisterRequestDTO;
import org.example.domain.OrderStatusRequestDTO;
import org.example.domain.OrderStatusResponseDTO;
import org.example.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rest")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping("register.do")
    @ResponseBody
    public void register(OrderRegisterRequestDTO orderRequest) {
        service.register(orderRequest);
    }

    @PostMapping("getOrderStatus.do")
    @ResponseBody
    public OrderStatusResponseDTO status(OrderStatusRequestDTO orderRequest) {
        return service.status(orderRequest);
    }

    @PostMapping("reverse.do")
    @ResponseBody
    public void cancel(OrderStatusRequestDTO orderRequest) {
        service.cancel(orderRequest);
    }

}
