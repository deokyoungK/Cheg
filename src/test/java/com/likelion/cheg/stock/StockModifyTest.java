package com.likelion.cheg.stock;

import com.likelion.cheg.domain.stock.Stock;
import com.likelion.cheg.domain.stock.StockRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockModifyTest {

    @Autowired
    StockRepository stockRepository;

    Stock stock = Stock.builder().id(1L).quantity(100).build();

    @Before
    public void init(){
        stockRepository.save(stock);
    }

    @Test
    public void 상품재고_감소(){
        stock.decrease(1);
        Stock findStock = stockRepository.findById(1L).orElseThrow();
        assertEquals(99,findStock.getQuantity());

    }



}
