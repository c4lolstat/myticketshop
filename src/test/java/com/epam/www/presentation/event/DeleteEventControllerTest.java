package com.epam.www.presentation.event;

import com.epam.www.dto.EventDTO;
import com.epam.www.service.IEventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;

/**
 * Created by Farkas on 2017.02.28..
 */
@RunWith(MockitoJUnitRunner.class)
public class DeleteEventControllerTest {


    private EventDTO eventDTO;

    @Mock
    private IEventService eventService;

    @InjectMocks
    private DeleteEventController deleteEventController = new DeleteEventController();

    @Before
    public void setup(){
        eventDTO = new EventDTO();
        eventDTO.setId(666);
        eventDTO.setAuditorium("Universal");
        eventDTO.setCounter(0);
        eventDTO.setEndDate(555L);
        eventDTO.setHour(10L);
        eventDTO.setPrice(24L);
        eventDTO.setStartDate(222L);
        eventDTO.setTitle("Jay and Silent Bob strike back");
    }

    @Test
    public void givenProperInputWhenEventDeletedThenResponseCorrect(){
        ResponseEntity<EventDTO> result = deleteEventController.deleteEvent(eventDTO);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenProperInputWhenEventDeletedThenServiceMethodCalled(){
        deleteEventController.deleteEvent(eventDTO);
        Mockito.verify(eventService, Mockito.times(1)).deleteEvent(anyInt());
    }

    @Test
    public void givenInputWithZeroIdWhenEventRetrievedThenResponseIsBadRequest(){
        eventDTO.setId(0);
        ResponseEntity<EventDTO> result = deleteEventController.deleteEvent(eventDTO);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
}
