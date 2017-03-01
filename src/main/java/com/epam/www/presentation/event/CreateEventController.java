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
@RequestMapping(value = "/createevent")
public class CreateEventController {

//    {"title":"Jay and Silent Bob strike back","startDate":"12345665","endDate":"14987456","hour":"4321","price":"990","counter":"0","auditorium":"Universal"}

    @Autowired
    private IEventService eventService;

    /**
     * createEvent. Persist new event data into DB. Using eventService.
     * @param eventDTO DTO that hold the data for the new event.
     * @return JSON with HTTP status.
     * */
    @RequestMapping(method = RequestMethod.POST,produces = "application/json")
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO){
        eventService.createEvent(eventDTO);
        return new ResponseEntity<EventDTO>(eventDTO, HttpStatus.OK);
    }
}
