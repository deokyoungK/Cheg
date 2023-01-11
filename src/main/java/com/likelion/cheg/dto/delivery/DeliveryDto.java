package com.likelion.cheg.dto.delivery;


import com.likelion.cheg.Annotation.phone.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeliveryDto {

    @NotBlank(message="NAME_IS_MANDATORY")
    private String name;

    @Phone
    private String phone;

    @NotBlank(message = "POSTCODE_IS_MANDATORY")
    private String postcode;
}
