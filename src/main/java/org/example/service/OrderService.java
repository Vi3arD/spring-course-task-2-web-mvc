package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Order;
import org.example.domain.OrderStatus;
import org.example.domain.OrderStatusResponse;
import org.example.exception.ItemAlreadyIsDeletedException;
import org.example.exception.ItemNotFoundException;
import org.example.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public OrderStatusResponse getById(long id) {
        Order order = repository.getById(id).orElseThrow(ItemNotFoundException::new);
        OrderStatusResponse osr = new OrderStatusResponse();
        osr.setOrderNumber(order.getOrderNumber());
        osr.setCurrency(order.getCurrency());
        osr.setAmount(order.getAmount());
        osr.setStatus(order.getStatus());
        return osr;
    }

    public Order save(Order order) {
        return repository.save(order).orElseThrow(ItemNotFoundException::new);
    }

    public void deposit(long id, int amount) {
        Order current = getOrderForModified(id);
        current.setAmount(amount);
        current.setStatus(OrderStatus.FINISHED_STATUS.getId());

        repository.update(current).orElseThrow(ItemNotFoundException::new);
    }

    public void cancel(long id) {
        Order current = getOrderForModified(id);
        current.setDeleted(true);

        repository.update(current).orElseThrow(ItemNotFoundException::new);
    }

    private Order getOrderForModified(long id) {
        Optional<Order> currentOptional = repository.getById(id);
        Order current = currentOptional.orElseThrow(ItemNotFoundException::new);

        if (current.isDeleted()) {
            throw new ItemAlreadyIsDeletedException();
        }

        return current;
    }

}
