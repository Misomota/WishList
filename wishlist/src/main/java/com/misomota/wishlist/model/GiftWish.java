package com.misomota.wishlist.model;

public class GiftWish {
    private String giftWishName;
    private int giftId;

    public GiftWish() {

    }

    public GiftWish(String giftWishName, int giftId) {
        this.giftWishName = giftWishName;
        this.giftId = giftId;
    }

    public String getGiftWishName() {
        return giftWishName;
    }

    public void setGiftWishName(String giftWishName) {
        this.giftWishName = giftWishName;
    }

    public int getGiftId() {
        return giftId;
    }

    public void setGiftId(int id) {
        this.giftId = id;
    }
}