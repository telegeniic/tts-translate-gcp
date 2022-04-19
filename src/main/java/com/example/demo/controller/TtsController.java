package com.example.demo.controller;

import com.example.demo.models.TTSRequest;
import com.example.demo.models.TTSConfig;
import com.example.demo.models.VoiceResponse;
import com.example.demo.services.TtsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/v1/tts/")
@CrossOrigin()
public class TtsController {

    private static final Logger logger = LoggerFactory.getLogger(TtsController.class);

    @Autowired
    TtsService service;

    @PostMapping("fr-CA/ssml")
    public String getAudioFromSSMLFrenchCA(TTSRequest req){
        req.getAudioConfigOptional()
                .ifPresentOrElse(a->logger.info("Audio config: {}",a.getAudioEncoding()),
                        () -> req.setAudioConfig(TTSConfig.getDefaultAudioConfig()));
        //If there is no voice config, then set a default
        req.getVoiceOptional()
                .ifPresentOrElse(v->logger.info(""),
                        () -> req.setVoice(TTSConfig.getFrenchDefaultVoice()));
        return service.generateTTS(req);
    }

    @PostMapping("en-US/ssml")
    public String getAudioFromSSMLEnglishUS(@RequestBody TTSRequest req){
        //If there is no audio config, then set a default
        req.getAudioConfigOptional()
                .ifPresentOrElse(a->{}, () -> req.setAudioConfig(TTSConfig.getDefaultAudioConfig()));
        //If there is no voice config, then set a default
        req.getVoiceOptional()
                .ifPresentOrElse(v->{}, () -> req.setVoice(TTSConfig.getEnglishDefaultVoice()));
        return service.generateTTS(req);
    }
    @GetMapping("get/all-voices")
    public List<VoiceResponse> getAllVoices() throws IOException {
        return TtsService.getVoiceList();
    }
}
