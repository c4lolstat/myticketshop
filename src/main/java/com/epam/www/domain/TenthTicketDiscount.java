package com.epam.www.domain;

/**
 * Created by Zoltan_Biro on 5/18/2017.
 */
public class TenthTicketDiscount implements Price{

    private Price basePrice;
    private final double discount = 0.5;

    public TenthTicketDiscount (Price price){
        this.basePrice = price;
    }

    @Override
    public long getSumPrice() {
        return (long)(discount * this.basePrice.getSumPrice());
    }
}
