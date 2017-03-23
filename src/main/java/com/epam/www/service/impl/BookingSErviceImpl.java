package com.epam.www.service.impl;

import com.epam.www.auth.JwtUtil;
import com.epam.www.dataaccess.dao.BookingDao;
import com.epam.www.dataaccess.dao.HibernateDaoFacade;
import com.epam.www.domain.Price;
import com.epam.www.dto.*;
import com.epam.www.service.BookingService;
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
    private HibernateDaoFacade hibernateDaoFacade;

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private JwtUtil jwtUtil;

    private int vipSeats;
    private int normalSeats;
    private EventDTO eventDTO;

    //TODO ask: how open should be a service? Is it adviced to define a public endpoint for everything?
    //TODO ask: how unit test effectively bookTicket?
    //TODO refactor this class when finished

    @Override
    public BookingInfoDTO getBookingInformation(Map<String, String> params) {
        EventDTO eventDTO = hibernateDaoFacade.readEventsWithParams(params);
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
        initialize(params);
        if (checkSeatsAreAvailableForEvent()) {
            fulfillBooking(params, request);
        }
    }

    private void initialize(Map<String, String> params) {
        vipSeats = Integer.parseInt(params.get("vipSeats"));
        normalSeats = Integer.parseInt(params.get("normalSeats"));
        eventDTO = hibernateDaoFacade.readEventsWithParams(params);
    }

    private void fulfillBooking(Map<String, String> params, HttpServletRequest request) {
        UserDTO userDTO = this.getUserInformationFromRequest(request);
        Price price = new Price.Calculator()
                .withBasePrice(eventDTO.getPrice())
                .withDiscount(userDTO.getDiscount())
                .withNormalSeats(normalSeats)
                .withVipSeats(vipSeats)
                .calculate();
        boolean payed = userDTO.getAccount() > price.getSumPrice();
        BookingDTO bookingDTO = new BookingDTO.BookingBuilder()
                .withUser(userDTO.getId())
                .withEvent(eventDTO.getId())
                .withVipSeats(Integer.parseInt(params.get("vipSeats")))
                .withNormalSeats(Integer.parseInt(params.get("normalSeats")))
                .withSumPrice(price.getSumPrice())
                .withBooked(!payed)
                .withPayed(payed)
                .build();
        this.transactBooking(userDTO, bookingDTO);
    }

    private UserDTO getUserInformationFromRequest(HttpServletRequest request){
        String authToken = jwtUtil.getTokenFromRequest(request);
        UserDTO userDTO = jwtUtil.parseToken(authToken);
        return hibernateDaoFacade.getUserById(userDTO.getId());
    }

    private boolean checkSeatsAreAvailableForEvent(){
        AvailableSeatsDTO availableSeatsDTO = this.getAvailableSeatNumbersForEvent(eventDTO);
        return !(availableSeatsDTO.getVipSeats() < vipSeats || availableSeatsDTO.getNormalSeats() < normalSeats);
    }

    @Transactional
    private void transactBooking(UserDTO userDTO, BookingDTO bookingDTO){
        bookingDao.createBooking(bookingDTO);
        if (bookingDTO.isPayed()){
            userDTO.setAccount(userDTO.getAccount()-bookingDTO.getSumPrice());
            hibernateDaoFacade.updateUser(userDTO);
        }

    }

    private AvailableSeatsDTO getAvailableSeatNumbersForEvent(EventDTO eventDTO){

        AvailableSeatsDTO availableSeats = new AvailableSeatsDTO(0,0);
        AuditoriumDTO auditorium = hibernateDaoFacade.readAuditoriumByName(eventDTO.getAuditorium());
        int vipSeatCount = bookingDao.countVipSeatsForEvent(eventDTO.getId());
        int normalSeatCount = bookingDao.countNormalSeatsForEvent(eventDTO.getId());

        Integer availableVipSeats = auditorium.getVipSeats() - vipSeatCount;
        Integer availableNormalSeats = auditorium.getNormalSeats() - normalSeatCount;
        availableSeats = new AvailableSeatsDTO(availableNormalSeats, availableVipSeats);

        return availableSeats;
    }



}
