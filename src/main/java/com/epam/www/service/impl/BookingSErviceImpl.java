package com.epam.www.service.impl;

import com.epam.www.auth.JwtUtil;
import com.epam.www.dataaccess.dao.BookingDao;
import com.epam.www.dataaccess.dao.HibernateDaoFacade;
import com.epam.www.domain.DiscountEnums;
import com.epam.www.domain.Price;
import com.epam.www.dto.*;
import com.epam.www.service.BookingService;
import com.epam.www.service.DiscountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

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
    private DiscountService discountService;

    @Autowired
    private PricingServiceImpl pricingService;

    @Autowired
    private JwtUtil jwtUtil;

    private int vipSeats;
    private int normalSeats;
    private EventDTO eventDTO;

    @Override
    public BookingInfoDTO getBookingInformation(int id) {
        EventDTO eventDTO = hibernateDaoFacade.readEventsWithParams(id);
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
    public void bookTicket(InitBookingDTO params, HttpServletRequest request) {
        initialize(params);
        if (checkSeatsAreAvailableForEvent()) {
            fulfillBooking(request);
        }
    }

    private void initialize(InitBookingDTO initBookingDTO) {
        this.vipSeats = initBookingDTO.getVipSeats();
        this.normalSeats = initBookingDTO.getNormalSeats();
        this.eventDTO = hibernateDaoFacade.readEventsWithParams(initBookingDTO.getId());
    }

    private void fulfillBooking(HttpServletRequest request) {
        UserDTO userDTO = this.getUserInformationFromRequest(request);

        List<DiscountEnums> discounts = discountService.getDiscountForUser(userDTO.getId());

        Price price = pricingService.getPrice(this.normalSeats, this.vipSeats, this.eventDTO.getPrice(), discounts);

        boolean payed = userDTO.getAccount().compareTo(price.getSumPrice()) == 1;
        BookingDTO bookingDTO = new BookingDTO.BookingBuilder()
                .withUser(userDTO.getId())
                .withEvent(this.eventDTO.getId())
                .withVipSeats(this.vipSeats)
                .withNormalSeats(this.normalSeats)
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
            userDTO.setAccount(userDTO.getAccount().subtract(bookingDTO.getSumPrice()));
            hibernateDaoFacade.updateUser(userDTO);
        }

    }

    private AvailableSeatsDTO getAvailableSeatNumbersForEvent(EventDTO eventDTO){

        AvailableSeatsDTO availableSeats = new AvailableSeatsDTO(0,0);
        AuditoriumDTO auditorium = hibernateDaoFacade.readAuditoriumByName(eventDTO.getAuditorium());
        Long vipSeatCount = bookingDao.countVipSeatsForEvent(eventDTO.getId());
        Long normalSeatCount = bookingDao.countNormalSeatsForEvent(eventDTO.getId());

        long availableVipSeats = auditorium.getVipSeats();
        long availableNormalSeats = auditorium.getNormalSeats();
        if(vipSeatCount != null){
            availableVipSeats -= vipSeatCount;
        }
        if(normalSeatCount != null){
            availableNormalSeats -= normalSeatCount;
        }
        availableSeats = new AvailableSeatsDTO(availableNormalSeats, availableVipSeats);

        return availableSeats;
    }



}
