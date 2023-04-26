package be.pxl.projects.api;

import jakarta.validation.constraints.NotBlank;

public record ContactTypeValue(@NotBlank(message = "Value should not be empty") String value) {
}
