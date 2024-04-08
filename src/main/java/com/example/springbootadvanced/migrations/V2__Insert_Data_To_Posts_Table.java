package com.example.springbootadvanced.migrations;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.flywaydb.core.internal.database.h2.H2DatabaseType;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public class V2__Insert_Data_To_Posts_Table extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        Connection connection = context.getConnection();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(connection, new H2DatabaseType());
        String sql = """
                insert into posts(title, description, author)
                values ('Test Post', 'Content of test post', 'Author1'),
                       ('Post1', 'Content of test Post1', 'Unknown');
                """;
        jdbcTemplate.update(sql);
    }
}
