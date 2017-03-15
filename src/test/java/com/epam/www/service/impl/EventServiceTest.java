package com.epam.www.service.impl;

import com.epam.www.dataaccess.dao.EventDao;
import com.epam.www.dataaccess.entity.Event;
import com.epam.www.dto.EventDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.refEq;

/**
 * Created by Farkas on 2017.02.28..
 */
@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    private Event event;

    @Mock
    private EventDao eventDao;

    @InjectMocks
    private EventServiceImpl eventService = new EventServiceImpl();

    @Before
    public void setup(){
        event = new Event();
        event.setId(666);
        event.setAuditorium("Universal");
        event.setCounter(0);
        event.setPrice(24L);
        event.setAirDate(222L);
        event.setTitle("Jay and Silent Bob strike back");
    }

    @Test
    public void whenEventCreatedThenDaoMethodCalled(){
        eventService.createEvent(any(EventDTO.class));
        Mockito.verify(eventDao, Mockito.times(1)).createEvent(any(EventDTO.class));
    }

    @Test
    public void whenEventDeletedThenDaoMethodCalled(){
        eventService.deleteEvent(anyInt());
        Mockito.verify(eventDao, Mockito.times(1)).deleteEvent(anyInt());
    }

    @Test
    public void whenEventUpdatedCreatedThenDaoMethodCalled(){
        eventService.updateEvent(any(EventDTO.class));
        Mockito.verify(eventDao, Mockito.times(1)).updateEvent(any(EventDTO.class));
    }

    @Test
    public void whenEventReadThenEventDTOReturned(){
        List<Event> evetsList = new ArrayList<>();
        evetsList.add(event);
        Mockito.when(eventDao.readEventsWithParams(any(Map.class))).thenReturn(evetsList);
        List<EventDTO> result = eventService.readEventsWithParams(any(Map.class));
        assertEquals(666, result.get(0).getId());
        assertEquals("Universal",result.get(0).getAuditorium());
        assertEquals(0,result.get(0).getCounter());
        assertEquals(24,result.get(0).getPrice());
        assertEquals(222, result.get(0).getAirDate());
        assertEquals("Jay and Silent Bob strike back",result.get(0).getTitle());
    }
}
