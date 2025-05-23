package com.gg.administrative_system_backend.contract;

import com.gg.administrative_system_backend.message.ValidationMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateContractDTO {
    @NotBlank(message = ValidationMessage.NAME_REQUIRED)
    private String name;
}
