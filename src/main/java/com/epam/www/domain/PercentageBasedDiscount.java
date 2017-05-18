package com.epam.www.domain;

/**
 * Created by Zoltan_Biro on 5/18/2017.
 */
public class PercentageBasedDiscount implements Price {

    private Price basePrice;
    private double discount;

    public PercentageBasedDiscount (Price price, double discount){
        this.basePrice = price;
        this.discount = discount;
    }

    @Override
    public long getSumPrice() {
        return (long)(this.discount * this.basePrice.getSumPrice());
    }
}
