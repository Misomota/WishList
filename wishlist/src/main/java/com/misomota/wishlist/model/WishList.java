package com.misomota.wishlist.model;

import java.util.List;

public class WishList {
    private String wishListName;
    private int id;
    private List<GiftWish> giftWishes;

    public WishList() {

    }

    public WishList(String wishListName, int id) {
        this.wishListName = wishListName;
        this.id = id;
    }

    public String getWishListName() {
        return wishListName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWishListName(String wishListName) {
        this.wishListName = wishListName;
    }

    public List<GiftWish> getGiftWishes() {
        return giftWishes;
    }

    public void setGiftWishes(List<GiftWish> giftWishes) {
        this.giftWishes = giftWishes;
    }
}
