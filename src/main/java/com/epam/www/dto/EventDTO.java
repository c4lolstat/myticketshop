package com.epam.www.dto;

import com.epam.www.dataaccess.entity.Event;
import com.sun.media.sound.AiffFileReader;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Farkas on 2017.02.23..
 */
public class EventDTO {

    private int id;
    private String title = "";
    private long price;
    private long airDate;
    private String auditorium = "";
    private long counter;

    public EventDTO(){}

    public EventDTO(Event event){
        this.id = event.getId();
        this.title = event.getTitle();
        this.price = event.getPrice();
        this.airDate = event.getAirDate();
        this.auditorium = event.getAuditorium();
        this.counter = event.getCounter();
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
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
