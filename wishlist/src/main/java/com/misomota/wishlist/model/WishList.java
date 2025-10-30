package com.misomota.wishlist.model;

import java.util.List;

public class WishList {
    private String nameOfWishList;
    private int id;
    private List<GiftWish> giftWishes;

    public WishList() {

    }

    public WishList(String wishListName, int id) {
        this.nameOfWishList = wishListName;
        this.id = id;
    }

    public String getNameOfWishList() {
        return nameOfWishList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWishListName(String wishListName) {
        this.nameOfWishList = wishListName;
    }

    public List<GiftWish> getGiftWishes() {
        return giftWishes;
    }

    public void setGiftWishes(List<GiftWish> giftWishes) {
        this.giftWishes = giftWishes;
    }
}
