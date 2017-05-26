package com.epam.www.presentation.event;

import com.epam.www.dto.EventDTO;
import com.epam.www.presentation.BaseController;
import com.epam.www.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Zoltan_Biro on 5/26/2017.
 */
@RestController
public class EventController  extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    /**
     * deleteEvent. Delete event data from DB. Using eventService.
     * @param id that hold the id of the event.
     * @return http status in JSON format.
     * */
    @RequestMapping(value = "/api/event/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<EventDTO> deleteEvent(@PathVariable("id") int id){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (id > 0){
            httpStatus = HttpStatus.OK;
            eventService.deleteEvent(id);
        }
        return new ResponseEntity<EventDTO>(httpStatus);
    }

    /**
     * createEvent. Persist new event data into DB. Using eventService.
     * @param eventDTO DTO that hold the data for the new event.
     * @return JSON with HTTP status.
     * */
    @RequestMapping(value = "/api/event", method = RequestMethod.POST,produces = "application/json")
    public ResponseEntity<EventDTO> createEvent(@Valid @RequestBody EventDTO eventDTO){
        eventService.createEvent(eventDTO);
        return new ResponseEntity<EventDTO>(eventDTO, HttpStatus.OK);
    }

    /**
     * getEvents. Select user data from DB. Using eventService.
     * @param params that hold the id of the event.
     * @return Event data in JSON format.
     * */
    @RequestMapping(value = "/api/event", method = RequestMethod.GET)
    public ResponseEntity<List<EventDTO>> getEvents(@RequestParam Map<String, String> params){
        List<EventDTO> result = Collections.emptyList();
        result = eventService.readEventsWithParams(params);
        return new ResponseEntity<List<EventDTO>>(result, HttpStatus.OK);
    }

    /**
     * updateEvent. Update event data from DB. Using userService.
     * @param eventDTO that hold the user email and password.
     * @return Event data in JSON format.
     * */
    @RequestMapping(value = "/api/event", method = RequestMethod.PUT)
    public ResponseEntity<EventDTO> updateEvent(@Valid @RequestBody EventDTO eventDTO){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (eventDTO.getId() > 0){
            httpStatus = HttpStatus.OK;
            eventService.updateEvent(eventDTO);
        }
        return new ResponseEntity<EventDTO>(eventDTO, httpStatus);
    }
}
