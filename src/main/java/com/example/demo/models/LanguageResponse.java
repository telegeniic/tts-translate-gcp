package com.example.demo.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LanguageResponse {

    private String name;
    private String code;
}
