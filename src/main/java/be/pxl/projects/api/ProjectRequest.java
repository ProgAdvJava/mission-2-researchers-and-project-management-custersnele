package be.pxl.projects.api;

import jakarta.validation.constraints.NotBlank;

public record ProjectRequest(@NotBlank(message="Project is required") String project) {
}
