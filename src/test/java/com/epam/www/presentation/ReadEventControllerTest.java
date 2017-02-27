package com.epam.www.presentation;

import com.epam.www.dto.EventDTO;
import com.epam.www.dto.UserDTO;
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
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

/**
 * Created by Farkas on 2017.02.27..
 */
@RunWith(MockitoJUnitRunner.class)
public class ReadEventControllerTest {

    private EventDTO eventDTO;

    @Mock
    private IEventService eventService;

    @InjectMocks
    private ReadEventController readEventController = new ReadEventController();

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
    public void givenProperInputWhenEventRetrievedThenResponseCorrect(){
        Mockito.when(eventService.readEventById(anyInt())).thenReturn(eventDTO);
        ResponseEntity<EventDTO> result = readEventController.getEvent(anyInt());
        assertEquals(eventDTO,result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenProperInputWhenEventRetrievedThenServiceMethodCalled(){
        Mockito.when(eventService.readEventById(anyInt())).thenReturn(eventDTO);
        readEventController.getEvent(anyInt());
        Mockito.verify(eventService, Mockito.times(1)).readEventById(anyInt());
    }
}
