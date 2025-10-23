package com.misomota.wishlist.repository;

import com.misomota.wishlist.model.WishList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class WishListRepository {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private final JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public WishList addWishList(String wishList) {
        String sql = "INSERT INTO WishList (wishListName) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(x -> {
            PreparedStatement preparedStatement = x.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,wishList);
            return preparedStatement;
        }, keyHolder);

        int wishListID = keyHolder.getKey() !=null ? keyHolder.getKey().intValue() : -1;

        if (wishListID != -1) {
            return new WishList(wishList);
        } else {
            throw new RuntimeException("Could not insert wishlist!");
        }
    }
}
