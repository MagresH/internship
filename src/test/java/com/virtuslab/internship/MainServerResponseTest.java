package com.virtuslab.internship;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainServerResponseTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void shouldReturnSameReceiptInJSON() throws Exception {
        // Given
        var productDb = new ProductDb();
        var cart = new Basket();
        var bread = productDb.getProduct("Bread");
        var cereals = productDb.getProduct("Cereals");
        var steak = productDb.getProduct("Steak");
        var onion = productDb.getProduct("Onion");
        cart.addProduct(onion);
        cart.addProduct(bread);
        cart.addProduct(bread);
        cart.addProduct(cereals);
        cart.addProduct(steak);

        var receiptGenerator = new ReceiptGenerator();

        // When
        var receipt = receiptGenerator.generate(cart);

        String list = "?list=" + onion.name() + "," + bread.name() + "," + bread.name() + "," + cereals.name() + "," + steak.name();

        MvcResult response = this.mvc.perform(MockMvcRequestBuilders.get("/receipt/" + list) //getting endopint response
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/json")).andReturn();

        String json_response = response.getResponse().getContentAsString(); //converting response to JSON string

        ObjectMapper mapper = new ObjectMapper();
        String json_receipt = mapper.writeValueAsString(receipt); //converting receipt object to JSON string

        assertEquals(json_receipt, json_response);

    }
}
