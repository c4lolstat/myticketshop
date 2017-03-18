package com.epam.www.dto;

/**
 * Created by Farkas on 2017.03.18..
 */
public class AvailableSeatsDTO {

    private int normalSeats;
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
