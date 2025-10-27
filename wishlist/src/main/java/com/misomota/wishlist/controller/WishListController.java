package com.misomota.wishlist.controller;

import com.misomota.wishlist.model.GiftWish;
import com.misomota.wishlist.model.WishList;
import com.misomota.wishlist.service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Gaveæsken")
public class WishListController {
    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping("/MyWishList")
    public String showWishList(Model model) {
        List<WishList> wishList = wishListService.showWishList();
        model.addAttribute("wishList", wishList);
        return "showWishList";
    }

    @GetMapping("/MyWishes")
    public String showGiftWishList(Model model) {
        List<GiftWish> giftWish = wishListService.showGiftWish();
        model.addAttribute("giftWish", giftWish);
        return "showGiftWish";
    }

    @GetMapping("/addWishList")
    public String addWishList(Model model) {
        WishList wishList = new WishList();
        model.addAttribute("WishList", wishList);
        return "addWishList";
    }

    @PostMapping("/addWishList")
    public String saveWishList(@ModelAttribute("WishList") WishList wishList) {
        wishListService.addWishList(wishList);
        return "redirect:/showWishList";
    }

    @GetMapping("/addWish")
    public String addWish(Model model) {
        GiftWish giftWish = new GiftWish();
        model.addAttribute("Wish", giftWish);
        return "addWish";
    }

    @PostMapping("/delete/wishList")
    public String deleteWishList(@RequestParam Integer id) {
        wishListService.deleteWishList(id);
        return "redirect:/showWishList";
    }

    @PostMapping("/delete/giftWish")
    public String deletegiftWish(@RequestParam Integer id) {
        wishListService.deleteGiftWish(id);
        return "redirect:/showWishList";
    }
}