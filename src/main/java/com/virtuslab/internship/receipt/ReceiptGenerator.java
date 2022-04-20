package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.FifteenPercentDiscount;
import com.virtuslab.internship.discount.TenPercentDiscount;
import com.virtuslab.internship.product.Product;

import java.util.*;

public class ReceiptGenerator {

    public static Receipt generate(Basket basket) {

        List<ReceiptEntry> receiptEntries = new ArrayList<>();

        for (Product product : basket.getProducts()) { //added foreach loop to select products from basket
            int quantity = Collections.frequency(basket.getProducts(), product); //checking the quantity of a product in the basket
            if (receiptEntries.contains(new ReceiptEntry(product, quantity))) {
                continue; // skipping repeats in the basket
            } else {
                receiptEntries.add(new ReceiptEntry(product, quantity)); //adding ReceiptEntry
            }
        }
        TenPercentDiscount tpd = new TenPercentDiscount();
        FifteenPercentDiscount fpd = new FifteenPercentDiscount();
        Receipt rp = new Receipt(receiptEntries);

        Receipt final_price = tpd.apply(fpd.apply(rp)); //passing receipt firstly to 15% discount method (applies if eligible),
        // then passing returned receipt to '10% discount' and applies discount if eligible;

        return final_price; //returning final receipt after checking for discounts
    }
}
