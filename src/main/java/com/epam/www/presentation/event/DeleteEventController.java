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
     * getUser. Select user data from DB. Using userService.
     * @param id that hold the user email and password.
     * @return User data in JSON format.
     * */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<EventDTO> deleteEvent(@RequestBody int id){
        eventService.deleteEvent(id);
        return new ResponseEntity<EventDTO>(HttpStatus.OK);
    }
}
