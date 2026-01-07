package com.muromtsev.employee.model.dto;

import com.muromtsev.employee.validation.ValidationPatterns;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EmployeeRequest(
        @NotBlank(message = "Требуется имя")
        @Size(message = "Имя должно быть от 2 до 15 символов", min = 2, max = 15)
        @Pattern(message = "Без спецсимволов (только буквы, пробелы)",
                regexp = ValidationPatterns.ALPHA_SPACES)
        String firstName,
        @Size(message = "Фамилия должна быть от 2 до 15 символов", min = 2, max = 15)
        @NotBlank(message = "Требуется фамилия")
        @Pattern(message = "Без спецсимволов (только буквы, пробелы)",
                regexp = ValidationPatterns.ALPHA_SPACES)
        String lastName,

        @NotBlank(message = "Требуется email")
        @Pattern(message = "Требуется корректный email адрес",
                regexp = ValidationPatterns.EMAIL)
        String email,
        @NotBlank(message = "Требуется должность")
        @Size(message = "Должность должна быть от 3 до 20 символов", min = 3, max = 20)
        @Pattern(message = "Без спецсимволов (только буквы, пробелы)",
                regexp = ValidationPatterns.ALPHA_SPACES)
        String position,

        @NotBlank(message = "Требуется UUID организации")
        @Pattern(regexp = ValidationPatterns.UUID)
        String organizationUuid
) {
}
