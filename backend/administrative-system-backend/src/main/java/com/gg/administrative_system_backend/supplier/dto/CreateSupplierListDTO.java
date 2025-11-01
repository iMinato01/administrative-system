package com.gg.administrative_system_backend.supplier.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CreateSupplierListDTO {
    @Valid
    @NotEmpty
    List<CreateSupplierDTO> listSupplier;
}
