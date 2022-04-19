package com.example.demo.services;

import com.example.demo.models.TTSConfig;
import com.example.demo.models.TTSRequest;
import com.example.demo.models.VoiceResponse;
import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TtsService {

    private static final Logger logger = LoggerFactory.getLogger(TtsService.class);
    private static final Base64.Encoder encoder = Base64.getEncoder(); //encoder to transform the bytes to text

    public String generateTTS(TTSRequest req){

        req.getInput().getSsml().ifPresent(ssml -> logger.info("ssml: {}", ssml));

        final String message = req
                .getInput()
                .getSsml()
                .orElseThrow();

        Optional<byte[]> tts = Optional.empty();
        try {
            tts = Optional.of(generateTTSBytes(req, message));
        } catch (IOException e) {
            logger.warn(e.getMessage());
            logger.warn(e.getCause().toString());
        }
        return tts
                .map(encoder::encodeToString)
                .orElseThrow();
    }

    private byte[] generateTTSBytes(@NotNull TTSConfig config, String ssml) throws IOException {
        try (TextToSpeechClient ttsClient = TextToSpeechClient.create()) {
            //set the text input in SSML format
            SynthesisInput input = SynthesisInput.newBuilder()
                    .setSsml(ssml)
                    .build();

            //build the voice request
            VoiceSelectionParams voice =
                    VoiceSelectionParams.newBuilder()
                            .setName(config.getVoice().getName())
                            .setLanguageCode(config.getVoice().getLanguageCode())
                            .setSsmlGender(SsmlVoiceGender.valueOf(config.getVoice().getSsmlGender()))
                            .build();

            //selection the audio encoding
            AudioConfig audioConfig =
                    AudioConfig.newBuilder()
                            .setAudioEncoding(AudioEncoding.valueOf(config.getAudioConfig().getAudioEncoding()))
                            .build();

            //perform the tts request
            SynthesizeSpeechResponse response = ttsClient.synthesizeSpeech(input, voice, audioConfig);

            //get the audio encoded in Base64
            ByteString audioContents = response.getAudioContent();
            logger.info("The audio has been generated successfully");
            return audioContents.toByteArray();
        }
    }

    public static List<VoiceResponse> getVoiceList() throws IOException{
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
            // Builds the text to speech list voices request
            ListVoicesRequest request = ListVoicesRequest.getDefaultInstance();

            // Performs the list voices request
            ListVoicesResponse response = textToSpeechClient.listVoices(request);
            List<Voice> voices = response.getVoicesList();

            return voices.stream()
                    .map(TtsService::fromVoiceToResponse)
                    .collect(Collectors.toList());
        }
    }

    private static VoiceResponse fromVoiceToResponse(Voice voice){
        return VoiceResponse.builder()
                .name(voice.getName())
                .supportedLanguage(voice
                        .getLanguageCodesList()
                        .asByteStringList()
                        .stream().map(ByteString::toStringUtf8)
                        .collect(Collectors.toList()))
                .voiceGender(voice.getSsmlGender().toString())
                .hertzRate(voice.getNaturalSampleRateHertz())
                .build();

    }
}
