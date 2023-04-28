package com.likelion.cheg.web.dto.pay;

import com.likelion.cheg.domain.cart.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.*;


@Data
public class PayCartResponseDto {
    private String productUrl;
    private String productBrand;
    private String productName;
    private int productCount;
    private int cartTotalPrice;
}
