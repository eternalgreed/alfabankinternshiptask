package ru.abdullaev.alfabankinternshiptask.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.abdullaev.alfabankinternshiptask.feignclients.CurrencyRateRequestClient;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Validated
@ConfigurationProperties(prefix = "openexchangerates")
public class CurrencyService {

    @NotBlank
    @Setter
    @Getter
    @Size(min = 32, max = 32)
    private String appId;

    @NotBlank
    @Setter
    @Getter
    private String symbols;

    private final CurrencyRateRequestClient currencyRateRequestClient;

    public int compareRates(String base) {
        Double latestRates = getTodayRate(base);
        String yesterdayDate = getYesterdayDate();
        Double yesterdayRates = getYesterdayRate(base, yesterdayDate);
        return latestRates.compareTo(yesterdayRates);
    }

    public Double getYesterdayRate(String base, String yesterdayDate) {
        Double yesterdayRates = currencyRateRequestClient.getHistoricalRates(appId, base, symbols, yesterdayDate).getRates().get(symbols);
        return yesterdayRates;
    }

    public Double getTodayRate(String base) {
        Double latestRates = currencyRateRequestClient.getLatestRates(appId, base, symbols).getRates().get(symbols);
        return latestRates;
    }

    private String getYesterdayDate() {
        return String.valueOf(LocalDate.now().minusDays(1));
    }



}
