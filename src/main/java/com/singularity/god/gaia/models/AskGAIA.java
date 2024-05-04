package com.singularity.god.gaia.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AskGAIA {
    @Schema(
            name = "question",
            description = "Ask GAIA anything you need to know",
            example = "If one of the ninja turtles gets ill, he'll go to the vet or to a human doctor?"
    )
    @NotNull(message = "Seriously? a null message ...")
    @NotEmpty(message = "You need to ask something! Don't waste GAIA time and transistors!")
    private String question;
}
