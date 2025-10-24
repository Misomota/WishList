package com.misomota.wishlist.model;

public class WishList {
    private String wishListName;

    public WishList() {

    }

    public WishList(String wishListName) {
        this.wishListName = wishListName;
    }

    public String getWishListName() {
        return wishListName;
    }

    public void setWishListName(String wishListName) {
        this.wishListName = wishListName;
    }
}
