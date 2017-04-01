package com.epam.www.service.impl;

import com.epam.www.dataaccess.dao.BookingDao;
import com.epam.www.dataaccess.dao.HibernateDaoFacade;
import com.epam.www.dataaccess.entity.Booking;
import com.epam.www.dto.*;
import com.epam.www.service.AuditoriumService;
import com.epam.www.service.BookingService;
import com.epam.www.service.EventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

/**
 * Created by Farkas on 2017.03.18..
 */
@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {

    public static final String TEST_AUDITORIUM_NAME = "testAuditorium";

    private EventDTO event;
    private AuditoriumDTO auditoriumDTO;
    private InitBookingDTO initBookingDTO;

    @Mock
    private BookingDao bookingDao;

    @Mock
    private HibernateDaoFacade hibernateDaoFacade;

    @Mock
    private HttpServletRequest httpServletRequest;

    @InjectMocks
    private BookingService bookingService = new BookingServiceImpl();

    @Before
    public void setup(){
        event = new EventDTO();
        event.setId(1);
        event.setAuditorium(TEST_AUDITORIUM_NAME);

        auditoriumDTO = new AuditoriumDTO();
        auditoriumDTO.setName(TEST_AUDITORIUM_NAME);
        auditoriumDTO.setNormalSeats(10);
        auditoriumDTO.setVipSeats(5);

        initBookingDTO = new InitBookingDTO();
        initBookingDTO.setId(1);
        initBookingDTO.setNormalSeats(5);
        initBookingDTO.setVipSeats(2);
    }

    @Test
    public void givenEventIdWhenCallAvailableSeatsThenMapWithSeatCountsReturned(){
        Mockito.when(hibernateDaoFacade.readEventsWithParams(anyInt())).thenReturn(event);
        Mockito.when(hibernateDaoFacade.readAuditoriumByName(anyString())).thenReturn(auditoriumDTO);
        Mockito.when(bookingDao.countNormalSeatsForEvent(anyInt())).thenReturn(5);
        Mockito.when(bookingDao.countVipSeatsForEvent(anyInt())).thenReturn(2);
        BookingInfoDTO result = bookingService.getBookingInformation(anyInt());
        assertEquals(5,result.getAvailableSeatsDTO().getNormalSeats());
        assertEquals(3,result.getAvailableSeatsDTO().getVipSeats());
    }
}
