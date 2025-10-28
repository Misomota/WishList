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

    private final RowMapper<WishList> wishListRowMapper = new RowMapper<>() {
        @Override
        public WishList mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new WishList(
                    rs.getString("wishListName"),
                    rs.getInt("id")
            );
        }
    };

    private final RowMapper<GiftWish> giftWishRowMapper = new RowMapper<>() {
        @Override
        public GiftWish mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new GiftWish(
                    rs.getString("giftWish")
            );
        }
    };

    public List<WishList> getWishList() {
        String sql = "SELECT WishListName FROM WishList";
        return jdbcTemplate.query(sql, wishListRowMapper);
    }

    public List<GiftWish> getGiftWish() {
        String sql = "SELECT WishName FROM GiftWish";
        return jdbcTemplate.query(sql, giftWishRowMapper);
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

    public WishList addWishList(WishList wishList, int id) {
        int name = insertAndReturnKey("INSERT INTO WishList (WishListName) VALUES (?)", wishList.getWishListName());
        if (name != -1) {
            return new WishList(wishList.getWishListName(),id);
        }
        else throw new RuntimeException("Could not insert wishlist!");
    }

    public GiftWish addGiftWish(GiftWish giftWish) {
        int name = insertAndReturnKey("INSERT INTO GiftWish (WishName) VALUES (?)", giftWish.getGiftWish());
        if (name != -1) {
            return new GiftWish(giftWish.getGiftWish());
        }
        else throw new RuntimeException("Could not insert giftlist!");
    }

    public void deleteGiftList(int GiftListID){
        String sqlDelete = "DELETE FROM GiftList where WishID = ?";
        jdbcTemplate.update(sqlDelete, GiftListID);
    }

    public WishList deleteWishList(int WishListID){
        String sqlDelete = "DELETE FROM WishList where WishListID = ?";
        jdbcTemplate.update(sqlDelete, WishListID);
        return null;
    }

    public void updateWishList(int WishID) {
        String sqlUpdate = "UPDATE WishName WHERE WishID = ?";
        jdbcTemplate.update(sqlUpdate, WishID);
    }

    public WishList findWishListById(int id) {
        String sql = "SELECT WishListID, WishListName FROM WishList WHERE WishListID = ?";
        return jdbcTemplate.queryForObject(sql, wishListRowMapper, id);
    }

}