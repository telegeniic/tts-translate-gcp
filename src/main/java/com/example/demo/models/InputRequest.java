package com.example.demo.models;

import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
public class InputRequest {
    private String ssml;

    public InputRequest(String ssml) {
        this.ssml = ssml;
    }

    public Optional<String> getSsml() {
        if(ssml==null)
            return Optional.empty();
        else
            return Optional.of(ssml);
    }

    public void setSsml(String ssml) {
        this.ssml = ssml;
    }
}
