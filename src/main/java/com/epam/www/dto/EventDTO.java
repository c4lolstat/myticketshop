package com.epam.www.dto;

import com.epam.www.dataaccess.entity.Event;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * Created by Farkas on 2017.02.23..
 */
public class EventDTO {

    private int id;
    @NotEmpty(message = "Title must not be empty!")
    private String title = "";
    @Min(value = 0, message = "Price cannot be negative!")
    private BigDecimal price;
    @Digits(integer = 10, fraction = 0 ,message = "Must be epoch time representation!")
    private long airDate;
    @NotEmpty(message = "Auditorium must not be empty!")
    private String auditorium = "";
    private long counter;
    private boolean active;

    public EventDTO(){}

    public EventDTO(Event event){
        this.id = event.getId();
        this.title = event.getTitle();
        this.price = event.getPrice();
        this.airDate = event.getAirDate();
        this.auditorium = event.getAuditorium();
        this.counter = event.getCounter();
        this.active = event.isActive();
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
