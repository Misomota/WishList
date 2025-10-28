package com.misomota.wishlist.service;

import com.misomota.wishlist.model.GiftWish;
import com.misomota.wishlist.model.WishList;
import com.misomota.wishlist.repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {
    private WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public List<WishList> showWishList() {
        return wishListRepository.getWishList();
    }

    public List<GiftWish> showGiftWish() {
        return wishListRepository.getGiftWish();
    }

    public WishList addWishList(WishList wishlist) {
        return wishListRepository.addWishList(wishlist);
    }

    public WishList deleteWishList(int WishListID) {
        return wishListRepository.deleteWishList(WishListID);
    }

    public WishList deleteGiftWish(int WishID) {
        return wishListRepository.deleteWishList(WishID);
    }

    public WishList updateWishList(int WishID) {
        wishListRepository.updateWishList(WishID);
        return wishListRepository.updateWishList(WishList.getId());
    }
}