package com.misomota.wishlist.controller;

import com.misomota.wishlist.model.GiftWish;
import com.misomota.wishlist.model.WishList;
import com.misomota.wishlist.service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Gaveæsken")
public class WishListController {
    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping("/addWishList")
    public String addWishList(Model model) {
        WishList wishList = new WishList();
        model.addAttribute("WishList", wishList);
        return "addWishList";
    }

    @GetMapping("/addWish")
    public String addWish(Model model) {
        GiftWish giftWish = new GiftWish();
        model.addAttribute("Wish", giftWish);
        return "addWish";
    }

}
