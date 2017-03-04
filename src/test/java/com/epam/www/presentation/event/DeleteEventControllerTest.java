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
import static org.mockito.Matchers.anyInt;

/**
 * Created by Farkas on 2017.02.28..
 */
@RunWith(MockitoJUnitRunner.class)
public class DeleteEventControllerTest {

    private static final int GOOD_ID = 2;
    private static final int BAD_ID = 0;

    @Mock
    private EventService eventService;

    @InjectMocks
    private DeleteEventController deleteEventController = new DeleteEventController();

    @Test
    public void givenProperInputWhenEventDeletedThenResponseCorrect(){
        ResponseEntity<EventDTO> result = deleteEventController.deleteEvent(GOOD_ID);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenProperInputWhenEventDeletedThenServiceMethodCalled(){
        deleteEventController.deleteEvent(GOOD_ID);
        Mockito.verify(eventService, Mockito.times(1)).deleteEvent(anyInt());
    }

    @Test
    public void givenInputWithZeroIdWhenEventRetrievedThenResponseIsBadRequest(){
        ResponseEntity<EventDTO> result = deleteEventController.deleteEvent(BAD_ID);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
}
