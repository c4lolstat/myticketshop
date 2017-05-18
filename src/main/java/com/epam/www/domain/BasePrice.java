package com.epam.www.domain;

/**
 * Created by Farkas on 2017.03.19..
 */
public class BasePrice implements Price{

    public static final long VIP_PRICE_MODIFIER = 2l;
    private long sumPrice;
    private int normalSeats;
    private int vipSeats;
    private long price;


    public BasePrice(int normalSeats, int vipSeats, long price) {
        this.normalSeats = normalSeats;
        this.vipSeats = vipSeats;
        this.price = price;
    }

    @Override
    public long getSumPrice() {
        return (vipSeats*VIP_PRICE_MODIFIER*price) + (normalSeats*price);
    }
}
