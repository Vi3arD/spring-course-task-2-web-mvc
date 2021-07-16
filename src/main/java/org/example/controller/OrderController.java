package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Order;
import org.example.domain.OrderStatusResponse;
import org.example.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    @ResponseBody
    public void register(Order order) {
        service.register(order);
    }

    @PostMapping(params = {"id", "amount"})
    @ResponseBody
    public void deposit(@RequestParam long id, @RequestParam int amount) {
        service.deposit(id, amount);
    }

    @GetMapping(params = "id")
    @ResponseBody
    public OrderStatusResponse status(@RequestParam long id) {
        return service.status(id);
    }

    @DeleteMapping(params = "id")
    @ResponseBody
    public void cancel(@RequestParam long id) {
        service.cancel(id);
    }

}
