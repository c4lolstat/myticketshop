package com.epam.www.presentation.event;

import com.epam.www.dto.EventDTO;
import com.epam.www.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Farkas on 2017.02.27..
 */
@RestController
@RequestMapping(value = "/api/event")
public class ReadEventController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadEventController.class);

    @Autowired
    private EventService eventService;

    /**
     * getEvents. Select user data from DB. Using eventService.
     * @param params that hold the id of the event.
     * @return Event data in JSON format.
     * */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EventDTO>> getEvents(@RequestParam Map<String, String> params){
        List<EventDTO> result = Collections.emptyList();
        result = eventService.readEventsWithParams(params);
        return new ResponseEntity<List<EventDTO>>(result, HttpStatus.OK);
    }
}
