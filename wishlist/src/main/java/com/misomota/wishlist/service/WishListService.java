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
        return wishListRepository.addWishList(wishlist, wishlist.getId());
    }

    public WishList deleteWishList(int WishListID) {
        return wishListRepository.deleteWishList(WishListID);
    }

    public GiftWish deleteGiftWish(int id) {
        return wishListRepository.deleteGiftList(id);
    }

    public WishList updateWishList(int wishID) {
        return wishListRepository.updateWishList(wishID);

    }

    public WishList findWishListByid(int id) {
        return wishListRepository.findWishListById(id);
    }
}