package com.misomota.wishlist.repository;

import com.misomota.wishlist.model.GiftWish;
import com.misomota.wishlist.model.WishList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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

    private final RowMapper<WishList> wishListRowMapper = (rs, rowNum) ->
            new WishList(
                    rs.getString("WishListName"),
                    rs.getInt("WishListID")
            );

    private final RowMapper<GiftWish> giftWishRowMapper = (rs, rowNum) ->
            new GiftWish(
                    rs.getString("WishName"),
                    rs.getInt("WishID")
            );

    public List<WishList> getWishList() {
        String sql = "SELECT WishListID, WishListName FROM WishList";
        return jdbcTemplate.query(sql, wishListRowMapper);
    }

    public WishList findWishListById(int id) {
        String sql = "SELECT WishListID, WishListName FROM WishList WHERE WishListID = ?";
        return jdbcTemplate.queryForObject(sql, wishListRowMapper, id);
    }

    public WishList addWishList(WishList wishList) {
        String sql = "INSERT INTO WishList (WishListName) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, wishList.getWishListName());
            return ps;
        }, keyHolder);

        int newId = keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
        return new WishList(wishList.getWishListName(), newId);
    }

    public void deleteWishList(int wishListId) {
        String sql = "DELETE FROM WishList WHERE WishListID = ?";
        jdbcTemplate.update(sql, wishListId);
    }

    public void updateWishList(WishList wishList) {
        String sql = "UPDATE WishList SET WishListName = ? WHERE WishListID = ?";
        jdbcTemplate.update(sql, wishList.getWishListName(), wishList.getId());
    }

    public List<GiftWish> getGiftWish() {
        String sql = "SELECT WishID, WishName FROM GiftWish";
        return jdbcTemplate.query(sql, giftWishRowMapper);
    }

    public GiftWish addGiftWish(GiftWish giftWish, int wishListId) {
        String sql = "INSERT INTO GiftWish (WishName, WishListID) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, giftWish.getGiftWish());
            ps.setInt(2, wishListId);
            return ps;
        }, keyHolder);

        int newId = keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
        return new GiftWish(giftWish.getGiftWish(), newId);
    }

    public void deleteGiftWish(int wishId) {
        String sql = "DELETE FROM GiftWish WHERE WishID = ?";
        jdbcTemplate.update(sql, wishId);
    }
}