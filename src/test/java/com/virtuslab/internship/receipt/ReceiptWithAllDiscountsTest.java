package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.ProductDb;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReceiptWithAllDiscountsTest {
    @Test
    void shouldGenerateReceiptForGivenBasketWithAllDiscounts() {
        // Given
        var productDb = new ProductDb();
        var cart = new Basket();
        var bread = productDb.getProduct("Bread");
        var cereals = productDb.getProduct("Cereals");
        var steak = productDb.getProduct("Steak");
        var onion = productDb.getProduct("Onion");
        var expectedTotalPrice = ((bread.price().multiply(BigDecimal.valueOf(2)).add(onion.price()).add(cereals.price()).add(steak.price())).multiply(BigDecimal.valueOf(0.85))).multiply(BigDecimal.valueOf(0.9));

        cart.addProduct(onion);
        cart.addProduct(bread);
        cart.addProduct(bread);
        cart.addProduct(cereals);
        cart.addProduct(steak);

        var receiptGenerator = new ReceiptGenerator();

        // When
        var receipt = receiptGenerator.generate(cart);

        // Then
        assertNotNull(receipt);
        assertEquals(4, receipt.entries().size());
        assertEquals(expectedTotalPrice, receipt.totalPrice());
        assertEquals(2, receipt.discounts().size());
    }
}
