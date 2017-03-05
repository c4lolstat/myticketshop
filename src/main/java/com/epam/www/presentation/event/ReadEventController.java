package com.epam.www.presentation.event;

import com.epam.www.dto.EventDTO;
import com.epam.www.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Farkas on 2017.02.27..
 */
@RestController
@RequestMapping(value = "/event/{id}")
public class ReadEventController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadEventController.class);

    @Autowired
    private EventService eventService;

    /**
     * getEvent. Select user data from DB. Using eventService.
     * @param id that hold the id of the event.
     * @return Event data in JSON format.
     * */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<EventDTO> getEvent(@PathVariable("id") int id){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        EventDTO result = new EventDTO();
        if (id > 0){
            httpStatus = HttpStatus.OK;
            result = eventService.readEventById(id);
        }
        return new ResponseEntity<EventDTO>(result, httpStatus);
    }
}
