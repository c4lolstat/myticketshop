package com.epam.www.domain;

import java.math.BigDecimal;

/**
 * Created by Zoltan_Biro on 6/2/2017.
 */
public class BasePrice implements Price {

    private BigDecimal price;

    public BasePrice(BigDecimal price){
        this.price = price;
    }

    @Override
    public BigDecimal getSumPrice() {
        return this.price;
    }

}
