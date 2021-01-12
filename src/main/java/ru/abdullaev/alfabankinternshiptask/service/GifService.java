package ru.abdullaev.alfabankinternshiptask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.abdullaev.alfabankinternshiptask.feignclients.GifRequestClient;
import ru.abdullaev.alfabankinternshiptask.model.GifModel;

@RequiredArgsConstructor
@Service
public class GifService {
    private final GifRequestClient gifRequestClient;

    @Value("${giphy.api_id}")
    private String apiId;


    public String getRandomGifUrl(String tag) {
        GifModel gifModel = gifRequestClient.getRandomGif(apiId, tag);
        String gifURL = (String) gifModel.getData().get("image_original_url");
        return gifURL;
    }


}
