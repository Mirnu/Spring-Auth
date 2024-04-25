package ru.mirn.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на аутентификацию")
public class SignUpRequest {
    @Schema(description = "Имя пользователя", example = "John")
    @Size(min = 5, max = 50, message = "Имя пользователя должно содержать от 5 до 50 символов")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    private String username;

    @Schema(description = "Пароль пользователя", example = "qwerty123")
    @Size(min = 8, max = 255, message = "Пароль должен содержать от 8 и до 255 символов")
    @NotBlank(message = "Пароль не должен быть пустым")
    private String password;
}
