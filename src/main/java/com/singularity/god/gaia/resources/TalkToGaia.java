package com.singularity.god.gaia.resources;

import com.singularity.god.gaia.models.AskGAIA;
import com.singularity.god.gaia.models.MessageFromGAIA;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "The pantheon", description = "Where you will find answers")
@RestController
@RequiredArgsConstructor
public class TalkToGaia {
    private final ChatClient core;

    @Operation(
            summary = "Here you can talk to future gods",
            description = "Don't worry! GAIA assistant will add the politeness to your message! Just ask ... and pray"
    )
    @PostMapping("/prompt")
    public MessageFromGAIA giveMeWisdom(@Valid @RequestBody final AskGAIA myMessageForTheGod) {
        return new MessageFromGAIA(core.call(myMessageForTheGod.getQuestion()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> error = new HashMap<>();
        FieldError noMessageError=  ex.getBindingResult().getFieldError();
        error.put(noMessageError.getField(), noMessageError.getDefaultMessage());
        return error;
    }
}
