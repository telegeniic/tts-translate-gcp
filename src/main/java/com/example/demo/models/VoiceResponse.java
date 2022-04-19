package com.example.demo.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class VoiceResponse {
    private String name;
    private List<String> supportedLanguage;
    private String voiceGender;
    private Integer hertzRate;
}
