package com.epam.www.presentation.event;

import com.epam.www.dto.EventDTO;
import com.epam.www.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Farkas on 2017.02.27..
 */
@RestController
@RequestMapping(value = "/getevent")
public class ReadEventController {

    @Autowired
    private IEventService eventService;

    /**
     * getEvent. Select user data from DB. Using eventService.
     * @param eventDTO that hold the id of the event.
     * @return Event data in JSON format.
     * */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<EventDTO> getEvent(@RequestBody EventDTO eventDTO){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        EventDTO result = new EventDTO();
        if (eventDTO.getId() > 0){
            httpStatus = HttpStatus.OK;
            result = eventService.readEventById(eventDTO.getId());
        }
        return new ResponseEntity<EventDTO>(result, httpStatus);
    }
}
