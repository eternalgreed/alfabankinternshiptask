package ru.abdullaev.alfabankinternshiptask.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.abdullaev.alfabankinternshiptask.model.CurrencyModel;

@FeignClient(name = "currency", url = "${openexchangerates.api}")
public interface CurrencyRateRequestClient {
    @GetMapping("/latest.json")
    CurrencyModel getLatestRates(@RequestParam(name = "app_id") String appId,
                                 @RequestParam(name = "base") String base,
                                 @RequestParam(name = "symbols") String symbols);
    @GetMapping("/historical/{date}.json")
    CurrencyModel getHistoricalRates(@RequestParam(name = "app_id") String appId,
                                     @RequestParam(name = "base") String base,
                                     @RequestParam(name = "symbols") String symbols,
                                     @PathVariable String date);


}
