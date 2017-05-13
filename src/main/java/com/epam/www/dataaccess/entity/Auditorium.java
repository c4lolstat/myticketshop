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
    private long vipSeats;
    private long normalSeats;

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

    public long getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(long  vipSeats) {
        this.vipSeats = vipSeats;
    }

    public long getNormalSeats() {
        return normalSeats;
    }

    public void setNormalSeats(long  normalSeats) {
        this.normalSeats = normalSeats;
    }
}
