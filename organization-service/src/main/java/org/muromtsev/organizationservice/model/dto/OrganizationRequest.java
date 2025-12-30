package org.muromtsev.organizationservice.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.muromtsev.organizationservice.validation.ValidationPatterns;

public record OrganizationRequest(
        @NotBlank(message = "Требуется имя организации")
        @Pattern(message = "Без спецсимволов (только буквы, цифры, пробелы и дефисы)",
                regexp = ValidationPatterns.NO_SPECIAL_CHARS)
        @Size(min = 3, max = 20, message = "Имя должно быть от 3 до 10 символов")
        String name,
        @NotBlank(message = "Требуется код организации")
        @Size(min = 3, max = 10, message = "Код должен быть от 3 до 10 символов")
        @Pattern(
                message = "Код должен начинаться с заглавной буквы и содержать от 3 до 20 символов (A-Z, А-Я, 0-9, _)",
                regexp = ValidationPatterns.ORGANIZATION_CODE)
        String code,
        @Size(min = 5, max = 50, message = "Описание должно быть от 5 до 50 символов")
        String description) {
}
