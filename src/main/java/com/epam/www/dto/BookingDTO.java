package com.epam.www.dto;

import com.epam.www.dataaccess.entity.Booking;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * Created by Farkas on 2017.03.15..
 */
public class BookingDTO {
    private int id;

    @NotEmpty(message = "Event must not be empty!")
    private int event;
    @NotEmpty(message = "User must not be empty!")
    private int user;
    @Min(value = 0, message = "Vip seats cannot be negative!")
    private int vipSeats;
    @Min(value = 0, message = "Normal seats cannot be negative!")
    private int normalSeats;
    private String discount="";
    @Min(value = 0, message = "Price cannot be negative!")
    private BigDecimal sumPrice;
    private boolean booked;
    private boolean payed;

    private BookingDTO(){}
    
    public static class BookingBuilder{
        private int event;
        private int user;
        private int vipSeats;
        private int normalSeats;
        private String discount="";
        private BigDecimal sumPrice;
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

        public BookingBuilder withSumPrice(BigDecimal sumPrice) {
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

        public BookingDTO build (Booking booking){
            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.event = booking.getEvent();
            bookingDTO.user = booking.getUser();
            bookingDTO.vipSeats = booking.getVipSeats();
            bookingDTO.normalSeats = booking.getNormalSeats();
            bookingDTO.discount = booking.getDiscount();
            bookingDTO.sumPrice = booking.getSumPrice();
            bookingDTO.booked = booking.isBooked();
            bookingDTO.payed = booking.isPayed();
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

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public boolean isBooked() {
        return booked;
    }

    public boolean isPayed() {
        return payed;
    }

}
