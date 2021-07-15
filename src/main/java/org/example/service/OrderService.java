package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Order;
import org.example.exception.ItemNotFoundException;
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
        return repository.getById(id).orElseThrow(ItemNotFoundException::new);
    }

    public Order save(Order order) {
        return repository.save(order).orElseThrow(ItemNotFoundException::new);
    }

    public void deposit(long id, int amount) {
        repository.deposit(id, amount).orElseThrow(ItemNotFoundException::new);
    }

    public void markDeleted(long id) {
        repository.markDeleted(id).orElseThrow(ItemNotFoundException::new);
    }

}
