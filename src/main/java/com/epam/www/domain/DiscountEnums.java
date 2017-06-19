package com.epam.www.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Farkas on 2017.05.13..
 */
public enum DiscountEnums {
    EVERY_TEN_BOOKING(0.5), FIVE_PERCENT(0.95), TEN_PERCENT(0.9), FIFTEEN_PERCENT(0.85), EMPTY(1.0);

    private BigDecimal modifier;

    DiscountEnums(Double modifier){
        this.modifier = BigDecimal.valueOf(modifier);
    }

    public BigDecimal getModifier(){
        return this.modifier;
    }
}
