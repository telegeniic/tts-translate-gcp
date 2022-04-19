package com.example.demo.services;

import com.example.demo.models.LanguageResponse;
import com.google.cloud.translate.Language;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TranslateService {

    public List<LanguageResponse> getAllLanguages(){
        Translate translate = TranslateOptions.getDefaultInstance().getService();
        return translate.listSupportedLanguages().stream()
                .map(this::fromLanguageToResponse).collect(Collectors.toList());

    }

    private LanguageResponse fromLanguageToResponse(Language language){
        return LanguageResponse.builder()
                .code(language.getCode())
                .name(language.getName())
                .build();
    }
}
