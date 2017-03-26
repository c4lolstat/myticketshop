package com.epam.www.dto;

import javax.validation.constraints.Min;

/**
 * Created by Farkas on 2017.03.26..
 */
public class InitBookingDTO {

    @Min(value = 0, message = "Event should be associated!")
    private int id;
    @Min(value = 0, message = "Normal seats cannot be negative!")
    private int normalSeats;
    @Min(value = 0, message = "Vip seats cannot be negative!")
    private int vipSeats;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNormalSeats() {
        return normalSeats;
    }

    public void setNormalSeats(int normalSeats) {
        this.normalSeats = normalSeats;
    }

    public int getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(int vipSeats) {
        this.vipSeats = vipSeats;
    }
}
