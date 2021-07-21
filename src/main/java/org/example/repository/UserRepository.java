package org.example.repository;

import org.example.domain.User;
import org.example.utils.RepositoryUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class UserRepository {
    private final JdbcTemplate template;

    private final RowMapper<User> rowMapper = (rs, i) -> new User(
            rs.getLong("id"),
            rs.getString("userName"),
            rs.getString("password")
    );

    public UserRepository(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    public Optional<User> getByName(String name) {
        return RepositoryUtils.queryForOptional(template,
                "SELECT id, " +
                        "userName, " +
                        "password " +
                        "FROM users " +
                        "WHERE userName = ?",
                rowMapper, name
        );
    }

}
