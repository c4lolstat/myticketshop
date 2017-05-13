package com.epam.www.presentation.statistic;

import com.epam.www.service.StatService;
import com.epam.www.service.impl.StatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Farkas on 2017.04.07..
 */
@RestController
@RequestMapping(value = "/api/stat/tickets")
public class EventInfo {

    @Autowired
    private StatService statService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<StatServiceImpl.EventStats>> getTicketingInfos(@RequestParam Map<String, String> params){
        List<StatServiceImpl.EventStats> result = Collections.emptyList();
        result = statService.getEventInfoByParams(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
