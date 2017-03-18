package com.epam.www.service.impl;

import com.epam.www.dataaccess.dao.BookingDao;
import com.epam.www.dto.AuditoriumDTO;
import com.epam.www.dto.AvailableSeatsDTO;
import com.epam.www.dto.BookingInfoDTO;
import com.epam.www.dto.EventDTO;
import com.epam.www.service.AuditoriumService;
import com.epam.www.service.BookingService;
import com.epam.www.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Farkas on 2017.03.15..
 */
@Component
public class BookingServiceImpl implements BookingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private EventService eventService;

    @Autowired
    private BookingDao bookingDao;


    //TODO ask: in service layer compose object of other services or daos?
    //TODO ask: how open should be a service? Is it adviced to define a public endpoint for everything?
    //TODO refactor this class when finished

    @Override
    public BookingInfoDTO getBookingInformation(Map<String, String> params) {
        EventDTO eventDTO = eventService.readEventsWithParams(params).get(0);
        AvailableSeatsDTO  availableSeatsDTO = this.getAvailableSeatNumbersForEvent(eventDTO);
        return new BookingInfoDTO(eventDTO, availableSeatsDTO);
    }


    private AvailableSeatsDTO getAvailableSeatNumbersForEvent(EventDTO eventDTO){

        AvailableSeatsDTO availableSeats = new AvailableSeatsDTO(0,0);
        AuditoriumDTO auditorium = auditoriumService.readAuditoriumByName(eventDTO.getAuditorium());
        int vipSeatCount = bookingDao.countVipSeatsForEvent(eventDTO.getId());
        int normalSeatCount = bookingDao.countNormalSeatsForEvent(eventDTO.getId());

        Integer availableVipSeats = auditorium.getVipSeats() - vipSeatCount;
        Integer availableNormalSeats = auditorium.getNormalSeats() - normalSeatCount;
        availableSeats = new AvailableSeatsDTO(availableNormalSeats, availableVipSeats);

        return availableSeats;
    }



}
