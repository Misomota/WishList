package com.misomota.wishlist.repository;

import com.misomota.wishlist.model.GiftWish;
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

    private int insertAndReturnKey(String sql, String value) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, value);
            return ps;
        }, keyHolder);
        return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
    }

    public WishList addWishList(String wishList) {
        int name = insertAndReturnKey("INSERT INTO WishList WHERE WishListName = ?", wishList);
        if (name != -1) {
            return new WishList(wishList);
        }
        else throw new RuntimeException("Could not insert wishlist!");
    }

    public GiftWish addGiftWish(String giftList) {
        int name = insertAndReturnKey("INSERT INTO GiftList WHERE WishName = ?", giftList);
        if (name != -1) {
            return new GiftWish(giftList);
        }
        else throw new RuntimeException("Could not insert giftlist!");
    }

    public void deleteGiftList(int GiftListID){
        String sqlDelete = "DELETE FROM GiftList where WishListID = ?";
        jdbcTemplate.update(sqlDelete, GiftListID);
    }

    public void deleteWishList(int WishID){
        String sqlDelete = "DELETE FROM WishList where WishID = ?";
        jdbcTemplate.update(sqlDelete, WishID);
    }
}
