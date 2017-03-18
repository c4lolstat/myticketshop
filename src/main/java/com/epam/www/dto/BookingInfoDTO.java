package com.epam.www.dto;

/**
 * Created by Farkas on 2017.03.18..
 */
public class BookingInfoDTO {

    private EventDTO eventDTO;
    private AvailableSeatsDTO availableSeatsDTO;

    private BookingInfoDTO(){}

    public BookingInfoDTO(EventDTO eventDTO, AvailableSeatsDTO availableSeatsDTO){
        this.eventDTO = eventDTO;
        this.availableSeatsDTO = availableSeatsDTO;
    }

    public EventDTO getEventDTO() {
        return eventDTO;
    }

    public AvailableSeatsDTO getAvailableSeatsDTO() {
        return availableSeatsDTO;
    }
}
