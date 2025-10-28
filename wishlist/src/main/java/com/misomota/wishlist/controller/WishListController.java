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
        List<WishList> listOfWishList = wishListService.showWishList();
        model.addAttribute("wishList", listOfWishList);
        return "showWishList";
    }

    @GetMapping("/MyWishes")
    public String showGiftWishList(Model model) {
        List<GiftWish> listOfGiftWish = wishListService.showGiftWish();
        model.addAttribute("giftWish", listOfGiftWish);
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
        return "redirect:/Gaveæsken/MyWishList";
    }

    @GetMapping("/addWish")
    public String addWish(@RequestParam("wishListId") int wishListId, Model model) {
        GiftWish giftWish = new GiftWish();
        giftWish.setGiftId(0);
        model.addAttribute("GiftWish", giftWish);
        model.addAttribute("wishListId", wishListId);
        return "addWish";
    }

    @PostMapping("/addWish")
    public String saveWish(@ModelAttribute("GiftWish") GiftWish giftWish,
                           @RequestParam("wishListId") int wishListId) {
        wishListService.addWish(giftWish, wishListId);
        return "redirect:/Gaveæsken/MyWishes";
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
            return "redirect:/Gaveæsken/MyWishList";
        }
    }

    @PostMapping("/update")
    public String updateWishList(@ModelAttribute("wishList") WishList wishlist) {
        wishListService.updateWishList(wishlist);
        return "redirect:/showWishList";
    }
}