package com.epam.www.presentation.event;

import com.epam.www.dto.EventDTO;
import com.epam.www.service.EventService;
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

/**
 * Created by Farkas on 2017.02.28..
 */
@RunWith(MockitoJUnitRunner.class)
public class UpdateEventControllerTest{
    private EventDTO eventDTO;

    @Mock
    private EventService eventService;

    @InjectMocks
    private UpdateEventController updateEventController = new UpdateEventController();

    @Before
    public void setup(){
        eventDTO = new EventDTO();
        eventDTO.setId(666);
        eventDTO.setAuditorium("Universal");
        eventDTO.setCounter(0);
        eventDTO.setPrice(24L);
        eventDTO.setAirDate(222L);
        eventDTO.setTitle("Jay and Silent Bob strike back");
    }

    @Test
    public void givenProperInputWhenEventUpdatedThenResponseCorrect(){
        ResponseEntity<EventDTO> result = updateEventController.updateEvent(eventDTO);
        assertEquals(eventDTO, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenProperInputWhenEventUpdatedThenServiceMethodCalled(){
        updateEventController.updateEvent(eventDTO);
        Mockito.verify(eventService, Mockito.times(1)).updateEvent(any(EventDTO.class));
    }

    @Test
    public void givenInputWithZeroIdWhenEventUpdatedThenResponseIsBadRequest(){
        eventDTO.setId(0);
        ResponseEntity<EventDTO> result = updateEventController.updateEvent(eventDTO);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
}
