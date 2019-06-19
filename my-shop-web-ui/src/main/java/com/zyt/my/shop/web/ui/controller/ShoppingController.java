package com.zyt.my.shop.web.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ShoppingController {

    @RequestMapping(value = "shoppingcart", method = RequestMethod.GET)
    private String showShoppingCart() {
        return "shoppingcart";
    }
}
