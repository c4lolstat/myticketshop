package com.epam.www.dataaccess.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Farkas on 2017.02.23..
 */

@Entity
@Table(name = "Event")
public class Event{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title = "";
    private BigDecimal price;
    private long airDate;
    private String auditorium = "";
    private long counter;
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getAirDate() {
        return airDate;
    }

    public void setAirDate(long airDate) {
        this.airDate = airDate;
    }

    public String getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(String auditorium) {
        this.auditorium = auditorium;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }
}
