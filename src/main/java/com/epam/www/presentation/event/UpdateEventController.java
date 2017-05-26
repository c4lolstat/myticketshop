package com.epam.www.presentation.event;

import com.epam.www.dto.EventDTO;
import com.epam.www.presentation.BaseController;
import com.epam.www.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Farkas on 2017.02.28..
 */
//@RestController
//@RequestMapping(value = "/api/event")
public class UpdateEventController extends BaseController{

//    {"title":"Dogma","startDate":"12345665","endDate":"14987456","hour":"4321","price":"1990","counter":"0","auditorium":"Universal"}

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateEventController.class);

    @Autowired
    private EventService eventService;

    /**
     * updateEvent. Update event data from DB. Using userService.
     * @param eventDTO that hold the user email and password.
     * @return Event data in JSON format.
     * */
//    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<EventDTO> updateEvent(@Valid @RequestBody EventDTO eventDTO){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (eventDTO.getId() > 0){
            httpStatus = HttpStatus.OK;
            eventService.updateEvent(eventDTO);
        }
        return new ResponseEntity<EventDTO>(eventDTO, httpStatus);
    }
}
