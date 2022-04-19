package com.example.demo.models;

public class TTSConfig {
    private AudioConfigRequest audioConfig;
    private VoiceRequest voice;

    public TTSConfig(){
    }

    public TTSConfig(AudioConfigRequest audioConfig, VoiceRequest voice) {
        this.audioConfig = audioConfig;
        this.voice = voice;
    }

    public static AudioConfigRequest getDefaultAudioConfig(){
        return new AudioConfigRequest("MP3");
    }

    public static VoiceRequest getEnglishDefaultVoice(){
        return new VoiceRequest("en-US", "en-US-Wavenet-G", "FEMALE");
    }

    public static VoiceRequest getFrenchDefaultVoice() {
        return new VoiceRequest("fr-CA", "fr-CA-Wavenet-A", "FEMALE");
    }

    public AudioConfigRequest getAudioConfig() {
        return audioConfig;
    }

    public void setAudioConfig(AudioConfigRequest audioConfig) {
        this.audioConfig = audioConfig;
    }

    public VoiceRequest getVoice() {
        return voice;
    }

    public void setVoice(VoiceRequest voice) {
        this.voice = voice;
    }
}
