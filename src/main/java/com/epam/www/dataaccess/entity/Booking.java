package com.epam.www.dataaccess.entity;

import javax.persistence.*;

/**
 * Created by Farkas on 2017.03.15..
 */
@Entity
@Table(name = "Auditorium")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int event;
    private int user;
    private int vipSeats;
    private int normalSeats;
    private String discount="";
    private long sumPrice;
    private boolean booked;
    private boolean payed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(int vipSeats) {
        this.vipSeats = vipSeats;
    }

    public int getNormalSeats() {
        return normalSeats;
    }

    public void setNormalSeats(int normalSeats) {
        this.normalSeats = normalSeats;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public long getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(long sumPrice) {
        this.sumPrice = sumPrice;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }
}
