package com.misomota.wishlist.controller;

import com.misomota.wishlist.model.GiftWish;
import com.misomota.wishlist.model.WishList;
import com.misomota.wishlist.service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Gaveaesken")
public class WishListController {
    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping("/MyWishList")
    public String showWishList(Model model) {
        List<WishList> listOfWishList = wishListService.showWishList();
        model.addAttribute("wishList", listOfWishList);
        return "showWishList";
    }

    @GetMapping("/MyWishes")
    public String showGiftWishList(@RequestParam("wishListId") int wishListId, Model model) {
        List<GiftWish> listOfGiftWish = wishListService.showGiftWish(wishListId);
        model.addAttribute("giftWish", listOfGiftWish);
        model.addAttribute("wishListId",wishListId);
        return "showGiftWish";
    }

    @GetMapping("/addWishList")
    public String addWishList(Model model) {
        model.addAttribute("WishList", new WishList());
        return "addWishList";
    }

    @PostMapping("/addWishList")
    public String saveWishList(@ModelAttribute("WishList") WishList wishList) {
        wishListService.addWishList(wishList);
        return "redirect:/Gaveaesken/MyWishList";
    }

    @GetMapping("/addWish")
    public String addWish(@RequestParam("wishListId") int wishListId, Model model) {
        GiftWish giftWish = new GiftWish();
        giftWish.setGiftId(0);
        model.addAttribute("giftWish", giftWish);
        model.addAttribute("wishListId", wishListId);
        return "addWish";
    }

    @PostMapping("/addWish")
    public String saveWish(@ModelAttribute("giftWish") GiftWish giftWish,
                           @RequestParam("wishListId") int wishListId) {
        wishListService.addWish(giftWish, wishListId);
        return "redirect:/Gaveaesken/MyWishes?wishListId=" + wishListId;
    }

    @PostMapping("/delete/wishList")
    public String deleteWishList(@RequestParam("id") int wishListId) {
        wishListService.deleteWishList(wishListId);
        return "redirect:/showWishList";
    }

    @PostMapping("/delete/giftWish")
    public String deletegiftWish(@RequestParam("id") int giftId) {
        wishListService.deleteGiftWish(giftId);
        return "redirect:/showWishList";
    }

    @GetMapping("/editWishList")
    public String editWishList(@RequestParam("id") int wishListId, Model model) {
        WishList wishList = wishListService.findWishListByid(wishListId);
        if (wishList != null) {
            model.addAttribute("WishList", wishList);
            return "editWishList";
        } else {
            return "redirect:/Gaveaesken/MyWishList";
        }
    }

    @PostMapping("/update")
    public String updateWishList(@ModelAttribute("wishList") WishList wishlist) {
        wishListService.updateWishList(wishlist);
        return "redirect:/showWishList";
    }
}