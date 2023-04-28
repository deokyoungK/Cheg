package com.likelion.cheg.web.dto.delivery;


import com.likelion.cheg.annotation.phone.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeliveryDto {

    @NotBlank(message="배송자 이름을 입력해주세요.")
    private String name;

    @Phone(message = "전화번호 형식이 올바르지 않습니다.")
    private String phone;

    @NotBlank(message = "주소는 필수 입력란입니다.")
    private String postcode;
}
