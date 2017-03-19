package com.epam.www.domain;

/**
 * Created by Farkas on 2017.03.19..
 */
public class Price {

    public static final long VIP_PRICE_MODIFIER = 2l;
    private long sumPrice;

    private Price() {
    }

    static public class Calculator {
        private long basePrice;
        private String discount;
        private int vipSeats;
        private int normalSeats;

        public Calculator withBasePrice(long price) {
            this.basePrice = price;
            return this;
        }

        public Calculator withDiscount(String discount) {
            this.discount = discount;
            return this;
        }

        public Calculator withNormalSeats(int normalSeats) {
            this.normalSeats = normalSeats;
            return this;
        }

        public Calculator withVipSeats(int vipSeats) {
            this.vipSeats = vipSeats;
            return this;
        }

        public Price calculate() {
            Price price = new Price();
            price.sumPrice = normalSeats * basePrice;
            price.sumPrice += vipSeats * basePrice * VIP_PRICE_MODIFIER;
            return price;
        }
    }

    public long getSumPrice() {
        return this.sumPrice;
    }
}
