package com.misomota.wishlist.model;

public class WishList {
    private String wishListName;
    private int id;

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
}
