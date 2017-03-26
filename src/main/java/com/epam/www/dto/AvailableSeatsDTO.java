package com.epam.www.dto;

import javax.validation.constraints.Min;

/**
 * Created by Farkas on 2017.03.18..
 */
public class AvailableSeatsDTO {

    @Min(value = 0, message = "Normal seats cannot be negative!")
    private int normalSeats;
    @Min(value = 0, message = "Vip seats cannot be negative!")
    private int vipSeats;

    private AvailableSeatsDTO(){}

    public AvailableSeatsDTO(int normal, int vip){
        this.normalSeats = normal;
        this.vipSeats = vip;
    }

    public int getNormalSeats() {
        return normalSeats;
    }

    public int getVipSeats() {
        return vipSeats;
    }

}
