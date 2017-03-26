package com.epam.www.dto;

import com.epam.www.dataaccess.entity.Auditorium;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;

/**
 * Created by Farkas on 2017.03.15..
 */
public class AuditoriumDTO {

    private int id;

    @NotEmpty(message = "Auditorium name must not be empty!")
    private String name = "";
    @Min(value = 0, message = "Vip seats cannot be negative!")
    private int vipSeats;
    @Min(value = 0, message = "Normal seats cannot be negative!")
    private int normalSeats;

    public AuditoriumDTO(){}

    public AuditoriumDTO(Auditorium auditorium){
        this.id = auditorium.getId();
        this.name = auditorium.getName();
        this.vipSeats = auditorium.getVipSeats();
        this.normalSeats = auditorium.getNormalSeats();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
