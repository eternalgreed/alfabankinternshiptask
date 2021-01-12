package ru.abdullaev.alfabankinternshiptask.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.abdullaev.alfabankinternshiptask.model.GifModel;

@FeignClient(value = "giphy", url = "${giphy.api_random}")
public interface GifRequestClient {
    @GetMapping
    GifModel getRandomGif(@RequestParam(name = "api_key") String apiKey, @RequestParam(name = "tag") String tag);

}
