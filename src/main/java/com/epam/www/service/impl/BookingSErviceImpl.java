package com.epam.www.service.impl;

import com.epam.www.auth.JwtUtil;
import com.epam.www.dataaccess.dao.BookingDao;
import com.epam.www.domain.Price;
import com.epam.www.dto.*;
import com.epam.www.service.AuditoriumService;
import com.epam.www.service.BookingService;
import com.epam.www.service.EventService;
import com.epam.www.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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
    private UserService userService;

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private JwtUtil jwtUtil;

    private int vipSeats;
    private int normalSeats;

    //TODO ask: in service layer compose object of other services or daos?
    //TODO ask: how open should be a service? Is it adviced to define a public endpoint for everything?
    //TODO aks: YAGNI and BDD is not contraversal OPEN-CLOSED-PRINCIPLE

    //TODO refactor this class when finished

    @Override
    public BookingInfoDTO getBookingInformation(Map<String, String> params) {
        EventDTO eventDTO = eventService.readEventsWithParams(params).get(0);
        AvailableSeatsDTO  availableSeatsDTO = this.getAvailableSeatNumbersForEvent(eventDTO);
        return new BookingInfoDTO(eventDTO, availableSeatsDTO);
    }

    @Override
    /*
    * params
    * Id (event)
    * normalseats
    * vipseats
    * */
    public void bookTicket(Map<String, String> params, HttpServletRequest request) {
        vipSeats = Integer.parseInt(params.get("vipSeats"));
        normalSeats = Integer.parseInt(params.get("normalSeats"));
        EventDTO eventDTO = this.getEventInformation(params);

        if (checkIfSeatsAreAvailable(params, eventDTO)) {
            UserDTO userDTO = this.getUserInformation(request);
            Price price = new Price.Calculator()
                    .withBasePrice(eventDTO.getPrice())
                    .withDiscount(userDTO.getDiscount())
                    .withNormalSeats(this.normalSeats)
                    .withVipSeats(this.vipSeats)
                    .calculate();

            boolean payed = userDTO.getAccount() > price.getSumPrice();

            BookingDTO booking = new BookingDTO.BookingBuilder()
                    .withUser(userDTO.getId())
                    .withEvent(eventDTO.getId())
                    .withVipSeats(Integer.parseInt(params.get("vipSeats")))
                    .withNormalSeats(Integer.parseInt(params.get("normalSeats")))
                    .withSumPrice(price.getSumPrice())
                    .withBooked(!payed)
                    .withPayed(payed)
                    .build();

            this.transactBooking();
        }
    }

    private UserDTO getUserInformation(HttpServletRequest request){
        String authToken = jwtUtil.getTokenFromRequest(request);
        UserDTO userDTO = jwtUtil.parseToken(authToken);
        return userService.getUserById(userDTO.getId());
    }

    private EventDTO getEventInformation(Map<String, String> params){
        return eventService.readEventsWithParams(params).get(0);
    }

    private boolean checkIfSeatsAreAvailable(Map<String, String> params, EventDTO eventDTO){
        AvailableSeatsDTO availableSeatsDTO= this.getAvailableSeatNumbersForEvent(eventDTO);
        return !(availableSeatsDTO.getVipSeats() < vipSeats || availableSeatsDTO.getNormalSeats() < normalSeats);

    }

    @Transactional
    private void transactBooking(){
        //TODO update user account + book ticket(s)
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
