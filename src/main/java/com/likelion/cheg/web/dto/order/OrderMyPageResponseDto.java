package com.likelion.cheg.web.dto.order;

import com.likelion.cheg.domain.enumType.DeliveryStatus;
import com.likelion.cheg.domain.enumType.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderMyPageResponseDto {
    private String orderNumber;
    private int orderProductCount;
    private OrderStatus orderStatus;
    private int orderPrice;
    private String productBrand;
    private String productUrl;
    private String productName;
    private DeliveryStatus deliveryStatus;
}
