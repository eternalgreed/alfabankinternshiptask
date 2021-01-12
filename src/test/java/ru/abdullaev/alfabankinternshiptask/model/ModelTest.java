package ru.abdullaev.alfabankinternshiptask.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

@JsonTest
public class ModelTest {
    @Autowired
    JacksonTester<CurrencyModel> currencyModelJacksonTester;
    @Autowired
    JacksonTester<GifModel> gifModelJacksonTester;

    @Test
    public void testGifResponse() throws IOException {
        GifModel gifModel = gifModelJacksonTester.read("/giphy.json").getObject();
        Assertions.assertEquals("https://media0.giphy.com/media/lmuXBIPNTYemLibZUO/giphy.gif", gifModel.getData().get("image_original_url"));
    }

    @Test
    public void testCurrencyResponse() throws IOException {
        CurrencyModel currencyModel = currencyModelJacksonTester.read("/rate10-01-2021.json").getObject();
        Assertions.assertEquals(Double.valueOf(74.193976), currencyModel.getRates().get("RUB"));

    }
}