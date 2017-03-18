package com.epam.www.service.impl;

import com.epam.www.dataaccess.dao.BookingDao;
import com.epam.www.dataaccess.entity.Booking;
import com.epam.www.dto.AuditoriumDTO;
import com.epam.www.dto.AvailableSeatsDTO;
import com.epam.www.dto.EventDTO;
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

    private Booking booking;
    private List<EventDTO> eventsList;
    private AuditoriumDTO auditoriumDTO;
    private Map<String, String> params;

    @Mock
    private BookingDao bookingDao;

    @Mock
    private EventService eventService;

    @Mock
    private AuditoriumService auditoriumService;

    @InjectMocks
    private BookingService bookingService = new BookingServiceImpl();

    @Before
    public void setup(){
        eventsList = new ArrayList<>();
        EventDTO event = new EventDTO();
        event.setId(1);
        event.setAuditorium(TEST_AUDITORIUM_NAME);
        eventsList.add(event);

        auditoriumDTO = new AuditoriumDTO();
        auditoriumDTO.setName(TEST_AUDITORIUM_NAME);
        auditoriumDTO.setNormalSeats(10);
        auditoriumDTO.setVipSeats(5);

        params = new HashMap<>();
        params.put("id","1");
    }
//TODO rewritetests
    //@Test
    public void givenEventIdWhenCallAvailableSeatsThenMapWithSeatCountsReturned(){
//        Mockito.when(eventService.readEventsWithParams(any(Map.class))).thenReturn(eventsList);
//        Mockito.when(auditoriumService.readAuditoriumByName(anyString())).thenReturn(auditoriumDTO);
//        Mockito.when(bookingDao.countNormalSeatsForEvent(anyInt())).thenReturn(5);
//        Mockito.when(bookingDao.countVipSeatsForEvent(anyInt())).thenReturn(2);
//        AvailableSeatsDTO result = bookingService.getAvailableSeatNumbersForEvent(eventsList.get(0));
//        assertEquals(5,result.getNormalSeats());
//        assertEquals(3,result.getVipSeats());
    }

    //@Test
    public void givenZeroEventIdWhenCallAvailableSeatsThenEmptyMapReturned(){
//        params.replace("id","0");
//        AvailableSeatsDTO result = bookingService.getAvailableSeatNumbersForEvent(eventsList.get(0));
//        assertTrue(result instanceof AvailableSeatsDTO);
    }
}
