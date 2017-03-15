package com.epam.www.dataaccess.entity;

import javax.persistence.*;

/**
 * Created by Farkas on 2017.03.15..
 */
@Entity
@Table(name = "Auditorium")
public class Auditorium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name = "";
    private int vipSteats;
    private int normalSeats;

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

    public int getVipSteats() {
        return vipSteats;
    }

    public void setVipSteats(int vipSteats) {
        this.vipSteats = vipSteats;
    }

    public int getNormalSeats() {
        return normalSeats;
    }

    public void setNormalSeats(int normalSeats) {
        this.normalSeats = normalSeats;
    }
}
