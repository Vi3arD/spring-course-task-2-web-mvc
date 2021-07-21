package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.*;
import org.example.exception.ItemAlreadyIsDeletedException;
import org.example.exception.ItemNotFoundException;
import org.example.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    public static final String NEW = "new";

    private final UserService userService;
    private final OrderRepository repository;

    public OrderStatusResponseDTO status(OrderStatusRequestDTO orderRequest) {
        userService.checkUser(orderRequest.getUserName(), orderRequest.getPassword());
        Order order = repository.getById(orderRequest.getOrderId()).orElseThrow(ItemNotFoundException::new);
        return getOrderStatusResponseFromOrder(order);
    }

    public Order register(OrderRegisterRequestDTO orderRequest) {
        User user = userService.checkUser(orderRequest.getUserName(), orderRequest.getPassword());
        return repository
                .save(getOrderFromOrderRequest(orderRequest, user.getId()))
                .orElseThrow(ItemNotFoundException::new);
    }

    public void cancel(OrderStatusRequestDTO orderRequest) {
        userService.checkUser(orderRequest.getUserName(), orderRequest.getPassword());

        Order current = getOrderForModified(orderRequest.getOrderId());
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

    private OrderStatusResponseDTO getOrderStatusResponseFromOrder(Order order) {
        return new OrderStatusResponseDTO(
                order.getOrderNumber(),
                order.getCurrency(),
                order.getAmount(),
                order.getStatus()
        );
    }

    private Order getOrderFromOrderRequest(OrderRegisterRequestDTO requestDTO, long userId) {
        return new Order(
                0,
                userId,
                requestDTO.getOrderNumber(),
                requestDTO.getAmount(),
                requestDTO.getCurrency(),
                requestDTO.getReturnUrl(),
                requestDTO.getFailUrl(),
                NEW,
                false
        );
    }

}
