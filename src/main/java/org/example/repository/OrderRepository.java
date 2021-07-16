package org.example.repository;

import org.example.domain.Order;
import org.example.domain.OrderStatus;
import org.example.exception.ItemAlreadyIsDeletedException;
import org.example.exception.ItemNotFoundException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {
    private final JdbcTemplate template;

    private final RowMapper<Order> rowMapper = (rs, i) -> new Order(
            rs.getLong("id"),
            rs.getString("userName"),
            rs.getString("password"),
            rs.getString("orderNumber"),
            rs.getInt("amount"),
            rs.getInt("currency"),
            rs.getString("returnUrl"),
            rs.getString("failUrl"),
            rs.getString("status"),
            rs.getBoolean("isDeleted")
    );

    public OrderRepository(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    public Optional<Order> getById(long id) {
        return queryForOptional(
                "SELECT id, " +
                        "userName, " +
                        "password, " +
                        "orderNumber, " +
                        "amount, " +
                        "currency, " +
                        "returnUrl, " +
                        "failUrl, " +
                        "status, " +
                        "isDeleted " +
                        "FROM orders " +
                        "WHERE id = ?",
                rowMapper, id
        );
    }

    public Optional<Order> save(Order order) {
        return queryForOptional(
                "INSERT INTO orders(" +
                        "userName, " +
                        "password, " +
                        "orderNumber, " +
                        "amount, " +
                        "currency, " +
                        "returnUrl, " +
                        "failUrl, " +
                        "status, " +
                        "isDeleted " +
                        ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                        "RETURNING id, " +
                        "userName, " +
                        "password, " +
                        "orderNumber, " +
                        "amount, " +
                        "currency, " +
                        "returnUrl, " +
                        "failUrl, " +
                        "status, " +
                        "isDeleted ",
                rowMapper,
                order.getUserName(),
                order.getPassword(),
                order.getOrderNumber(),
                order.getAmount(),
                order.getCurrency(),
                order.getReturnUrl(),
                order.getFailUrl(),
                OrderStatus.NEW_STATUS.getId(),
                false
        );
    }

    public Optional<Order> update(Order order) {
        return queryForOptional(
                "UPDATE orders SET " +
                        "userName = ?, " +
                        "password = ?, " +
                        "orderNumber = ?, " +
                        "amount = ?, " +
                        "currency = ?, " +
                        "returnUrl = ?, " +
                        "failUrl = ?, " +
                        "status = ?, " +
                        "isDeleted = ? " +
                        "WHERE id = ? " +
                        "RETURNING id, " +
                        "userName, " +
                        "password, " +
                        "orderNumber, " +
                        "amount, " +
                        "currency, " +
                        "returnUrl, " +
                        "failUrl, " +
                        "status, " +
                        "isDeleted ",
                rowMapper,
                order.getUserName(),
                order.getPassword(),
                order.getOrderNumber(),
                order.getAmount(),
                order.getCurrency(),
                order.getReturnUrl(),
                order.getFailUrl(),
                order.getStatus(),
                order.isDeleted(),
                order.getId()
        );
    }

    private <T> Optional<T> queryForOptional(String sql, RowMapper<T> rowMapper, Object... args) {
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(
                sql, rowMapper, args
        )));
    }

}
