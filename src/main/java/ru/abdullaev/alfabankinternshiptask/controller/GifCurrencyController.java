package ru.abdullaev.alfabankinternshiptask.controller;

import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.abdullaev.alfabankinternshiptask.service.CurrencyService;
import ru.abdullaev.alfabankinternshiptask.service.GifService;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class GifCurrencyController {

    private final GifService gifService;
    private final CurrencyService currencyService;


    @GetMapping("/api/{currency_code}")
    public String getGif(@PathVariable(name = "currency_code") String code, Model model) {
        int diff = currencyService.compareRates(code);
        if (diff == 0) {
            return "Курс не изменился!";
        }
        String gifUrl;
        if (diff > 0) {
            gifUrl = gifService.getRandomGifUrl("rich");
            model.addAttribute("gifUrl", gifUrl);
            return "<img src=\"" +
                    gifUrl + "\"/>" + " Курс стал выше!";

        } else {
            gifUrl = gifService.getRandomGifUrl("broke");
            return "<img src=\"" +
                    gifUrl + "\"/>" + " Курс стал ниже!";
        }
    }

    @ExceptionHandler({FeignException.class
    })
    public ResponseEntity<String> handleException(FeignException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
