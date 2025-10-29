package com.misomota.wishlist.model;

public class GiftWish {
    private String giftWishName;
    private int id;

    public GiftWish() {

    }

    public GiftWish(String giftWish, int id) {
        this.giftWishName = giftWish;
        this.id = id;
    }

    public String getGiftWishName() {
        return giftWishName;
    }

    public void setGiftWishName(String giftWish) {
        this.giftWishName = giftWish;
    }

    public int getGiftId() {
        return id;
    }

    public void setGiftId(int id) {
        this.id = id;
    }
}