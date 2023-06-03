package com.likelion.cheg.domain.stock;

import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.handler.ErrorCode;
import com.likelion.cheg.handler.ex.CustomBusinessApiException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "stock",fetch = LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    private int quantity;

    //변경 필요
    public void setProduct(Product product){
        this.product = product;
    }

    public void decrease(int quantity){
        if(this.quantity - quantity < 0){
            throw new CustomBusinessApiException(ErrorCode.NOT_ENOUGH_STOCK);
        }
        this.quantity -= quantity;
    }

    public void increase(int quantity){
        this.quantity += quantity;
    };

}
