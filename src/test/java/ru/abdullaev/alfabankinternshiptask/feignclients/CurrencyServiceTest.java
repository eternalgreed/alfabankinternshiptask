package ru.abdullaev.alfabankinternshiptask.feignclients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.ResourceUtils;
import ru.abdullaev.alfabankinternshiptask.service.CurrencyService;

import java.io.File;
import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class CurrencyServiceTest {
    @Autowired
    CurrencyService service;

    static WireMockServer wireMockServer = new WireMockServer();

    @BeforeAll
    static void startServer() {
        wireMockServer.start();
    }

    @AfterAll
    static void stopServer() {
        wireMockServer.stop();
    }

    @Test
    void testCurrencyFromFileAndFromService() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File fileWithJson = ResourceUtils.getFile("classpath:rate10-01-2021.json");
        Double rateFromFile = mapper.readTree(fileWithJson).get("rates").get("RUB").asDouble();
        stubFor(any(anyUrl())
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n" +
                                "  \"disclaimer\": \"Usage subject to terms: https://openexchangerates.org/terms\",\n" +
                                "  \"license\": \"https://openexchangerates.org/license\",\n" +
                                "  \"timestamp\": 1610323198,\n" +
                                "  \"base\": \"USD\",\n" +
                                "  \"rates\": {\n" +
                                "    \"BTC\": 0.000026105702,\n" +
                                "    \"EUR\": 0.820533,\n" +
                                "    \"RUB\": 74.193976\n" +
                                "  }\n" +
                                "}")));
        Double rateFromService = service.getTodayRate("USD");
        Assertions.assertEquals(rateFromFile, rateFromFile);
    }
}
