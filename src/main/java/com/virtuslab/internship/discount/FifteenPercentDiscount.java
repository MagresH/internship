package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;

import java.math.BigDecimal;
import java.util.List;

public class FifteenPercentDiscount {
    public static String NAME = "FifteenPercentDiscount";

    public Receipt apply(Receipt receipt) {
        if (shouldApply(receipt)) {
            var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.85));
            List<String> discounts = receipt.discounts();
            discounts.add(NAME);
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    }

    private boolean shouldApply(Receipt receipt) {//checking if quantity of GRAINS type products are equal or bigger than 3
        int grains_quantity = 0;
        for (ReceiptEntry iterator : receipt.entries()) {
            int quantity = iterator.quantity();
            String type = String.valueOf(iterator.product().type());
            if (type == "GRAINS") grains_quantity += quantity;
        }
        if (grains_quantity >= 3) return true;
        return false;
    }
}