package ru.abdullaev.alfabankinternshiptask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.abdullaev.alfabankinternshiptask.feignclients.CurrencyRateRequestClient;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    @Value("${openexchangerates.api}")
    private String ratesUrl;

    @Value("${openexchangerates.app_id}")
    private String appId;

    @Value("${openexchangerates.currency_rate_to}")
    private String symbols;

    private final CurrencyRateRequestClient currencyRateRequestClient;

    public int compareRates(String base){
        Double latestRates = currencyRateRequestClient.getLatestRates(appId, base, symbols).getRates().get(symbols);
        String yesterdayDate = getYesterdayDate();
        Double yesterdayRates = currencyRateRequestClient.getHistoricalRates(appId, base, symbols, yesterdayDate).getRates().get(symbols);
        return latestRates.compareTo(yesterdayRates);
    }

    private String getYesterdayDate() {
        return String.valueOf(LocalDate.now().minusDays(1));
    }


}
