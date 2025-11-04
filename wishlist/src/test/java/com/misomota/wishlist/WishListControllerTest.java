package com.misomota.wishlist;

import com.misomota.wishlist.controller.WishListController;
import com.misomota.wishlist.model.WishList;
import com.misomota.wishlist.service.WishListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class WishListControllerTest {

    private MockMvc mockMvc; //Simulerer HTTP-requests til controlleren

    @Mock //Mocking af service-laget

    private WishListService wishListService;

    @InjectMocks //Injector controlleren med mocks
    private WishListController wishListController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(wishListController).build();
    }

    @Test
    void testShowWishList() throws Exception {

        WishList wish1 = new WishList();
        wish1.setId(1);
        wish1.setWishListName("Test WishList");

        when(wishListService.showWishList()).thenReturn(Arrays.asList(wish1)); //Vi fortæller mocken, hvad den skal returnere

        mockMvc.perform(get("/Gaveaesken/MyWishList")) //Vi laver en GET-request til endpointet

                //Tjekker status, view-navn og model-attributter
                .andExpect(status().isOk())
                .andExpect(view().name("showWishList"))
                .andExpect(model().attributeExists("wishList"))
                .andExpect(model().attribute("wishList", Arrays.asList(wish1)));

        verify(wishListService, times(1)).showWishList(); //Sikrer at vores service blev kaldt korrekt
    }
}