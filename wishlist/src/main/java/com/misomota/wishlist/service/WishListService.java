package com.misomota.wishlist.service;

import com.misomota.wishlist.model.WishList;
import com.misomota.wishlist.repository.WishListRepository;
import org.springframework.stereotype.Service;

@Service
public class WishListService {
    private WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public WishList addWishList(WishList wishlist) {
        return wishListRepository.addWishList(wishlist);
    }

    public WishList deleteWishList(int WishListID) {
        return wishListRepository.deleteWishList(WishListID);
    }
}