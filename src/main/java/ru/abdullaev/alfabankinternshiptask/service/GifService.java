package ru.abdullaev.alfabankinternshiptask.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.abdullaev.alfabankinternshiptask.feignclients.GifRequestClient;
import ru.abdullaev.alfabankinternshiptask.model.GifModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Service
@Validated
@ConfigurationProperties(prefix = "giphy")
public class GifService {
    private final GifRequestClient gifRequestClient;

    @NotBlank
    @Size(min = 32, max = 32)
    @Setter
    private String appId;


    public String getRandomGifUrl(String tag) {
        GifModel gifModel = gifRequestClient.getRandomGif(appId, tag);
        String gifURL = (String) gifModel.getData().get("image_original_url");
        return gifURL;
    }


}
