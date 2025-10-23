package com.misomota.wishlist.service;

import com.misomota.wishlist.repository.WishListRepository;

@Service
public class WishListService {

    private final WishListRepository repository;

    public WishListService(WishListRepository repository) {
        this.repository = repository;
    }

}
