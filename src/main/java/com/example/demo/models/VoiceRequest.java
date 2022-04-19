package com.example.demo.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoiceRequest {
    private String languageCode;
    private String name;
    private String ssmlGender;
}
