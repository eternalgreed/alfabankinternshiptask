package ru.abdullaev.alfabankinternshiptask.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.abdullaev.alfabankinternshiptask.feignclients.CurrencyRateRequestClient;
import ru.abdullaev.alfabankinternshiptask.feignclients.GifRequestClient;
import ru.abdullaev.alfabankinternshiptask.model.CurrencyModel;
import ru.abdullaev.alfabankinternshiptask.model.GifModel;
import ru.abdullaev.alfabankinternshiptask.service.CurrencyService;
import ru.abdullaev.alfabankinternshiptask.service.GifService;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GifCurrencyController.class)
class GifCurrencyControllerTest {

    @MockBean
    private CurrencyRateRequestClient currencyRateRequestClient;
    @MockBean
    private GifRequestClient gifRequestClient;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CurrencyService currencyService;
    @MockBean
    GifService gifService;

    @Test
    void testGifCurrencyController() throws Exception {
        GifModel gifModel = new GifModel();
        gifModel.setData(Map.of("image_original_url", "https://media0.giphy.com/media/lmuXBIPNTYemLibZUO/giphy.gif"));
        CurrencyModel today = new CurrencyModel();
        today.setRates(Map.of("RUB", 74.822));
        CurrencyModel yesterday = new CurrencyModel();
        today.setRates(Map.of("RUB", 74.193976));
        Mockito.when(currencyRateRequestClient.getLatestRates(any(), any(), any())).thenReturn(today);
        Mockito.when(currencyRateRequestClient.getHistoricalRates(any(), any(), any(), any())).thenReturn(yesterday);
        Mockito.when(gifRequestClient.getRandomGif(any(), any())).thenReturn(gifModel);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/USD"))
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertNotNull(mvcResult.toString());
    }

}