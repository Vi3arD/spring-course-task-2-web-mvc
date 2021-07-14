package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Order;
import org.example.exception.ItemNotFound;
import org.example.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public List<Order> getAll() {
        return repository.getAll();
    }

    public Order getById(long id) {
        return repository.getById(id).orElseThrow(ItemNotFound::new);
    }

    public Order save(Order order) {
        return repository.save(order).orElseThrow(ItemNotFound::new);
    }

    public void paid(long id, int amount) {
        repository.paid(id, amount).orElseThrow(ItemNotFound::new);
    }

    public void markDeleted(long id) {
        repository.markDeleted(id).orElseThrow(ItemNotFound::new);
    }

}
