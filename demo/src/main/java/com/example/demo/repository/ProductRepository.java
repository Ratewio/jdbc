package com.example.demo.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final String sql = read("sql/find_products_by_name.sql");

    public ProductRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getProductName(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", name);

        return jdbcTemplate.queryForList(sql, params, String.class);
    }

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader =
                     new BufferedReader(new InputStreamReader(is))) {

            return bufferedReader.lines()
                    .collect(Collectors.joining("\n"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
