package ru.nabokovsg.gateway.dto.company_service.contact;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные новых реквизитов")
public class NewContactDto {

    @Schema(description = "Номер телефона")
    @NotBlank(message = "phone should not be blank")
    private String phone;
    @Schema(description = "Факс")
    private String fax;
    @Schema(description = "электронная почта")
    @NotBlank(message = "email should not be blank")
    @Email(message = "email invalid")
    private String email;
}