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
 * Created by Farkas on 2017.02.28..
 */
@RestController
@RequestMapping(value = "/event/{id}")
public class DeleteEventController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteEventController.class);

    @Autowired
    private EventService eventService;

    /**
     * deleteEvent. Delete event data from DB. Using eventService.
     * @param id that hold the id of the event.
     * @return http status in JSON format.
     * */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<EventDTO> deleteEvent(@PathVariable("id") int id){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (id > 0){
            httpStatus = HttpStatus.OK;
            eventService.deleteEvent(id);
        }
        return new ResponseEntity<EventDTO>(httpStatus);
    }
}
