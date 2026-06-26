package com.courtademanuel.mi_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductDTO(@NotBlank(message = "The name must not be empty")
                         String name,

                         @NotNull(message = "Price must not be null")
                         @Positive(message = "Price should be bigger than 0!")
                         Double price) {}
