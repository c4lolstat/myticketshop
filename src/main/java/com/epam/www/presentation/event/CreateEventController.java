package com.epam.www.presentation.event;

/**
 * Created by Farkas on 2017.02.26..
 */

import com.epam.www.dto.EventDTO;
import com.epam.www.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateEventController {

    @Autowired
    private IEventService eventService;

    /**
     * createUser. Persist new user data into DB. Using userService.
     * @param eventDTO DTO that hold the data for the new user.
     * @return JSON with HTTP status.
     * */
    @RequestMapping(method = RequestMethod.POST,produces = "application/json")
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO){
        eventService.createEvent(eventDTO);
        return new ResponseEntity<EventDTO>(eventDTO, HttpStatus.OK);
    }
}
