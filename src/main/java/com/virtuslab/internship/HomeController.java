package com.virtuslab.internship;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.exception.ApiRequestException;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
public class HomeController {


    @RequestMapping("/")
    public String home() { //page with cart generator

        return "home.jsp";

    }

    @RequestMapping("/receipt")
    @ResponseBody
    public Receipt receiptPage(String list) { //endpoint generating a receipt from the given list
        if (StringUtils.isBlank(list))
            throw new ApiRequestException("No products specified");//checking if a list has been given and if it is empty

        List<String> myList = new ArrayList<>(Arrays.asList(list.split(","))); //creating a list for the products specified in request

        var productDb = new ProductDb();

        Basket cart = new Basket();

        for (String product : myList) {
            product = product.toLowerCase();//formatting product names to "Xyyy"
            String firstLetter = product.substring(0, 1);
            String remainingLetters = product.substring(1);
            product = firstLetter.toUpperCase() + remainingLetters;

            try {                               //checking if a product with the given name exists
                cart.addProduct(productDb.getProduct(product)); //adding product to cart
            } catch (NoSuchElementException e) {    //if the product name does not match the database, a message is displayed
                throw new ApiRequestException("At least one of the products is not recognized");
            }
        }
        var receiptGenerator = new ReceiptGenerator();
        Receipt receipt = receiptGenerator.generate(cart);
        return receipt;
    }

    @RequestMapping("/**")
    public void errorPage() {
        throw new ApiRequestException("Page not found");
    } //handling remaining errors

}



