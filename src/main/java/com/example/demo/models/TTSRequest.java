package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
public class TTSRequest extends TTSConfig{
    private InputRequest input;

    public Optional<AudioConfigRequest> getAudioConfigOptional() {
        if(getAudioConfig() == null) return Optional.empty();
        else return Optional.of(getAudioConfig());
    }

    public Optional<VoiceRequest> getVoiceOptional() {
        if(getVoice() == null) return Optional.empty();
        else return Optional.of(getVoice());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TTSRequest that = (TTSRequest) o;
        return Objects.equals(input, that.input);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), input);
    }
}
