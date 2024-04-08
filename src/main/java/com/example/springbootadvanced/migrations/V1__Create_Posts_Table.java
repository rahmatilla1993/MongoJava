package com.example.springbootadvanced.migrations;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.flywaydb.core.internal.database.h2.H2DatabaseType;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public class V1__Create_Posts_Table extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        Connection connection = context.getConnection();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(connection, new H2DatabaseType());
        String sql = """
                create table posts
                (
                    id          serial primary key,
                    title       varchar not null,
                    description varchar not null,
                    author      varchar not null
                );
                """;
        jdbcTemplate.execute(sql);
    }
}
