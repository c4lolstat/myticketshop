package com.epam.www.dto;

import javax.validation.constraints.Min;

/**
 * Created by Farkas on 2017.03.18..
 */
public class AvailableSeatsDTO {

    @Min(value = 0, message = "Normal seats cannot be negative!")
    private long normalSeats;
    @Min(value = 0, message = "Vip seats cannot be negative!")
    private long vipSeats;

    private AvailableSeatsDTO(){}

    public AvailableSeatsDTO(long normal, long vip){
        this.normalSeats = normal;
        this.vipSeats = vip;
    }

    public long getNormalSeats() {
        return normalSeats;
    }

    public long getVipSeats() {
        return vipSeats;
    }

}
