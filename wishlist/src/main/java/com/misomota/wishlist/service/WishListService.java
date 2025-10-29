package com.misomota.wishlist.service;

import com.misomota.wishlist.model.GiftWish;
import com.misomota.wishlist.model.WishList;
import com.misomota.wishlist.repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {
    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public List<WishList> showWishList() {
        return wishListRepository.getWishList();
    }

    public List<GiftWish> showGiftWish(int wishListId) {
        WishList wishList = wishListRepository.findWishListById(wishListId);
        if (wishList != null) {
            return wishList.getGiftWishes();
        }
        return new ArrayList<>();
    }

    public WishList addWishList(WishList wishlist) {
        return wishListRepository.addWishList(wishlist);
    }

    public GiftWish addWish(GiftWish giftWish, int wishlistId) {
        return wishListRepository.addGiftWish(giftWish, wishlistId);
    }

    public void deleteWishList(int WishListID) {
        wishListRepository.deleteWishList(WishListID);
    }

    public void deleteGiftWish(int wishId) {
        wishListRepository.deleteGiftWish(wishId);
    }

    public void updateWishList(WishList wishlist) {
         wishListRepository.updateWishList(wishlist);

    }

    public WishList findWishListByid(int id) {
        return wishListRepository.findWishListById(id);
    }

    /**
     * Tilføj et gaveønske til en ønskeliste.
     * @param giftWish selve ønsket (navn)
     * @param wishListId id'et på ønskelisten det hører til
     */
}