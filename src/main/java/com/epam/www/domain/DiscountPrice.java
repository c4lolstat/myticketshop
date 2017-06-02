package com.epam.www.domain;

import java.math.BigDecimal;

/**
 * Created by Farkas on 2017.03.19..
 */
public class DiscountPrice implements Price{
    
    private DiscountEnums dicount;
    private Price price;


    public DiscountPrice(Price price, DiscountEnums dicount) {
        this.price = price;
        this.dicount = dicount;
    }

    @Override
    public BigDecimal getSumPrice() {
        return price.getSumPrice().multiply(this.dicount.getModifier());
    }
}
