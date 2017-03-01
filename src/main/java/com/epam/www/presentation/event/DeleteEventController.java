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
 * Created by Farkas on 2017.02.28..
 */
@RestController
@RequestMapping(value = "/deleteevent")
public class DeleteEventController {


    @Autowired
    private IEventService eventService;

    /**
     * deleteEvent. Delete event data from DB. Using eventService.
     * @param eventDTO that hold the id of the event.
     * @return http status in JSON format.
     * */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<EventDTO> deleteEvent(@RequestBody EventDTO eventDTO){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (eventDTO.getId() > 0){
            httpStatus = HttpStatus.OK;
            eventService.deleteEvent(eventDTO.getId());
        }
        return new ResponseEntity<EventDTO>(httpStatus);
    }
}
