package org.example.repository;

import org.example.domain.Order;
import org.example.utils.RepositoryUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class OrderRepository {
    private final JdbcTemplate template;

    private final RowMapper<Order> rowMapper = (rs, i) -> new Order(
            rs.getLong("id"),
            rs.getLong("userId"),
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
        return RepositoryUtils.queryForOptional(template,
                "SELECT id, " +
                        "userId, " +
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
        return RepositoryUtils.queryForOptional(template,
                "INSERT INTO orders(" +
                        "userId, " +
                        "orderNumber, " +
                        "amount, " +
                        "currency, " +
                        "returnUrl, " +
                        "failUrl, " +
                        "status, " +
                        "isDeleted " +
                        ") VALUES(?, ?, ?, ?, ?, ?, ?, ?) " +
                        "RETURNING id, " +
                        "userId, " +
                        "orderNumber, " +
                        "amount, " +
                        "currency, " +
                        "returnUrl, " +
                        "failUrl, " +
                        "status, " +
                        "isDeleted ",
                rowMapper,
                order.getUserId(),
                order.getOrderNumber(),
                order.getAmount(),
                order.getCurrency(),
                order.getReturnUrl(),
                order.getFailUrl(),
                order.getStatus(),
                order.isDeleted()
        );
    }

    public Optional<Order> update(Order order) {
        return RepositoryUtils.queryForOptional(template,
                "UPDATE orders SET " +
                        "userId = ?, " +
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
                order.getUserId(),
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

}
