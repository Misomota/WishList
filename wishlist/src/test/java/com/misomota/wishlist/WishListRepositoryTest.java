package com.misomota.wishlist;

import com.misomota.wishlist.model.WishList;
import com.misomota.wishlist.repository.WishListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:h2init.sql", executionPhase = BEFORE_TEST_METHOD)
public class WishListRepositoryTest {

    @Autowired
    private WishListRepository wishListRepository;

    @Test
    void getWishList() {
        /* WishList wishList = new WishList();*/
        List<WishList> wishList = wishListRepository.getWishList();

        assertThat(wishList).isNotNull();
        assertThat(wishList.size()).isEqualTo(2);
        assertThat(wishList.get(0).getWishListName()).isEqualTo("test");
        assertThat(wishList.get(1).getWishListName()).isEqualTo("fefe");
    }

}
