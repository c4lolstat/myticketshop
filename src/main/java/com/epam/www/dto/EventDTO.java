package com.epam.www.dto;

import com.epam.www.dataaccess.entity.Event;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Farkas on 2017.02.23..
 */
public class EventDTO {

    private String title = "";
    private long hash;
    private long price;
    private LocalDate startDate = LocalDate.MIN;
    private LocalDate endDate = LocalDate.MIN;
    private LocalTime hour = LocalTime.MIDNIGHT;
    private String auditorium = "";
    private long counter;

    private EventDTO(){}

    private EventDTO(Event event){
        this.title = event.getTitle();
        this.hash = event.getHash();
        this.price = event.getPrice();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.hour = event.getHour();
        this.auditorium = event.getAuditorium();
        this.counter = event.getCounter();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getHash() {
        return hash;
    }

    public void setHash(long hash) {
        this.hash = hash;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
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
