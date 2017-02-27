package com.epam.www.dataaccess.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

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
    private long price;
    private long startDate;
    private long endDate;
    private long hour;
    private String auditorium = "";
    private long counter;

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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public long getHour() {
        return hour;
    }

    public void setHour(long hour) {
        this.hour = hour;
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
