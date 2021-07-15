package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Order;
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
    public Order register(Order order) {
        return service.save(order);
    }

    @PostMapping(params = {"id", "amount"})
    @ResponseBody
    public void deposit(@RequestParam long id, @RequestParam int amount) {
        service.deposit(id, amount);
    }

    @GetMapping(params = "id")
    @ResponseBody
    public Order status(@RequestParam long id) {
        return service.getById(id);
    }

    @DeleteMapping(params = "id")
    @ResponseBody
    public void cancel(@RequestParam long id) {
        service.cancel(id);
    }

}
