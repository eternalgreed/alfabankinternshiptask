package ru.abdullaev.alfabankinternshiptask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CurrencyModel {
    @Setter
    @Getter
    private Map<String, Double> rates;
}
