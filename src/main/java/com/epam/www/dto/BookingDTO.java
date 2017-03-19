package com.epam.www.dto;

/**
 * Created by Farkas on 2017.03.15..
 */
public class BookingDTO {
    private int id;

    private int event;
    private int user;
    private int vipSeats;
    private int normalSeats;
    private String discount="";
    private long sumPrice;
    private boolean booked;
    private boolean payed;

    private BookingDTO(){}
    
    public static class BookingBuilder{
        private int event;
        private int user;
        private int vipSeats;
        private int normalSeats;
        private String discount="";
        private long sumPrice;
        private boolean booked;
        private boolean payed;

        public BookingBuilder withEvent(int event) {
            this.event = event;
            return this;
        }

        public BookingBuilder withUser(int user) {
            this.user = user;
            return this;
        }

        public BookingBuilder withVipSeats(int vipSeats) {
            this.vipSeats = vipSeats;
            return this;
        }

        public BookingBuilder withNormalSeats(int normalSeats) {
            this.normalSeats = normalSeats;
            return this;
        }

        public BookingBuilder withDiscount(String discount) {
            this.discount = discount;
            return this;
        }

        public BookingBuilder withSumPrice(long sumPrice) {
            this.sumPrice = sumPrice;
            return this;
        }

        public BookingBuilder withBooked(boolean booked) {
            this.booked = booked;
            return this;
        }

        public BookingBuilder withPayed(boolean payed) {
            this.payed = payed;
            return this;
        }

        public BookingDTO build (){
            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.event = this.event;
            bookingDTO.user = this.user;
            bookingDTO.vipSeats = this.vipSeats;
            bookingDTO.normalSeats = this.normalSeats;
            bookingDTO.discount = this.discount;
            bookingDTO.sumPrice = this.sumPrice;
            bookingDTO.booked = this.booked;
            bookingDTO.payed = this.payed;
            return bookingDTO;
        }
    }
    
    public int getId() {
        return id;
    }

    public int getEvent() {
        return event;
    }

    public int getUser() {
        return user;
    }

    public int getVipSeats() {
        return vipSeats;
    }

    public int getNormalSeats() {
        return normalSeats;
    }

    public String getDiscount() {
        return discount;
    }

    public long getSumPrice() {
        return sumPrice;
    }

    public boolean isBooked() {
        return booked;
    }

    public boolean isPayed() {
        return payed;
    }

}
