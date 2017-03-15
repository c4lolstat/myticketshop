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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;

/**
 * Created by Farkas on 2017.02.27..
 */
@RunWith(MockitoJUnitRunner.class)
public class ReadEventControllerTest {

    private  Map<String,String> params= new HashMap<>();

    private List<EventDTO> evetsList;

    private EventDTO eventDTO;

    @Mock
    private EventService eventService;

    @InjectMocks
    private ReadEventController readEventController = new ReadEventController();

    @Before
    public void setup(){
        eventDTO = new EventDTO();
        eventDTO.setId(666);
        eventDTO.setAuditorium("Universal");
        eventDTO.setCounter(0);
        eventDTO.setPrice(24L);
        eventDTO.setAirDate(222L);
        eventDTO.setTitle("Jay and Silent Bob strike back");

        evetsList = new ArrayList<>();
        evetsList.add(eventDTO);
    }

    @Test
    public void givenProperInputWhenEventsRetrievedThenResponseCorrect(){
        params.put("id", "1");
        Mockito.when(eventService.readEventsWithParams(any(Map.class))).thenReturn(evetsList);
        ResponseEntity<List<EventDTO>> result = readEventController.getEvents(params);
        assertEquals(eventDTO,result.getBody().get(0));
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenProperInputWhenEventsRetrievedThenServiceMethodCalled(){
        params.put("id", "1");
        readEventController.getEvents(params);
        Mockito.verify(eventService, Mockito.times(1)).readEventsWithParams(params);
    }

    @Test
    public void givenInputWithZeroParamWhenEventsRetrievedThenAllEventsReturned(){
        Mockito.when(eventService.readEventsWithParams(any(Map.class))).thenReturn(evetsList);
        ResponseEntity<List<EventDTO>> result = readEventController.getEvents(params);
        assertEquals(eventDTO,result.getBody().get(0));
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenInputWithZeroParamWhenEventsRetrievedThenServiceMethodCalled(){
        readEventController.getEvents(params);
        Mockito.verify(eventService, Mockito.times(1)).readEventsWithParams(params);
    }
}
