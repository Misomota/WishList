package com.misomota.wishlist.model;

public class GiftWish {
    private String giftWish;
    private int id;

    public GiftWish() {

    }

    public GiftWish(String giftWish, int id) {
        this.giftWish = giftWish;
        this.id = id;
    }

    public String getGiftWish() {
        return giftWish;
    }

    public void setGiftWish(String giftWish) {
        this.giftWish = giftWish;
    }

    public int getGiftId() {
        return id;
    }

    public void setGiftId(int id) {
        this.id = id;
    }
}