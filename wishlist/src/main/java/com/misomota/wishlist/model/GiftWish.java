package com.misomota.wishlist.model;

public class GiftWish {
    private String giftWishName;
    private int giftId;

    public GiftWish() {

    }

    public GiftWish(String giftWish, int giftId) {
        this.giftWishName = giftWish;
        this.giftId = giftId;
    }

    public String getGiftWishName() {
        return giftWishName;
    }

    public void setGiftWishName(String giftWish) {
        this.giftWishName = giftWish;
    }

    public int getGiftId() {
        return giftId;
    }

    public void setGiftId(int id) {
        this.giftId = id;
    }
}