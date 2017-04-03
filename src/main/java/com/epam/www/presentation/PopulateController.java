package com.epam.www.presentation;

import com.epam.www.dataaccess.entity.Auditorium;
import com.epam.www.dto.AuditoriumDTO;
import com.epam.www.dto.EventDTO;
import com.epam.www.dto.UserDTO;
import com.epam.www.service.AuditoriumService;
import com.epam.www.service.EventService;
import com.epam.www.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farkas on 2017.03.07..
 */
@RestController
@RequestMapping(value = "/populate")
public class PopulateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PopulateController.class);

    private final ObjectMapper mapper = new ObjectMapper();
    private List<String> userList = new ArrayList<>();
    private List<String> eventList = new ArrayList<>();
    private List<String> auditoriumList = new ArrayList<>();

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private AuditoriumService auditoriumService;

    @RequestMapping(method = RequestMethod.GET)
    public void populate(){
        init();
        addEvents();
        addUsers();
        addAuditoriums();
    }

    private void init(){
        eventList.add("{\"title\":\"Jay and Silent Bob strike back\",\"airDate\":\"1489564967\",\"price\":\"990\",\"counter\":\"0\",\"auditorium\":\"Universal\",\"active\":\"true\"}");
        eventList.add("{\"title\":\"Dogma\",\"airDate\":\"1489600967\",\"price\":\"1290\",\"counter\":\"0\",\"auditorium\":\"Illumination\",\"active\":\"true\"}");
        eventList.add("{\"title\":\"Shop Stop\",\"airDate\":\"1489665767\",\"price\":\"1550\",\"counter\":\"0\",\"auditorium\":\"Disney\",\"active\":\"true\"}");
        eventList.add("{\"title\":\"Mallrats\",\"airDate\":\"1489683767\",\"price\":\"800\",\"counter\":\"0\",\"auditorium\":\"Fox\",\"active\":\"true\"}");

        userList.add("{\"firstName\":\"Magnolia\", \"lastName\":\"Rajongo\",\"password\":\"1234\",\"email\":\"kevin.smith@gmail.com\",\"account\":555,\"discount\":\"normal\"}");
        userList.add("{\"firstName\":\"Silent\", \"lastName\":\"Bob\",\"password\":\"1111\",\"email\":\"silent.bob@gmail.com\",\"account\":555,\"discount\":\"normal\"}");
        userList.add("{\"firstName\":\"Holden\", \"lastName\":\"McNiel\",\"password\":\"1234\",\"email\":\"ben.affleck@gmail.com\",\"account\":555,\"discount\":\"normal\"}");
        userList.add("{\"firstName\":\"Dakon\", \"lastName\":\"Vago\",\"password\":\"1234\",\"email\":\"mark.hamill@gmail.com\",\"account\":555,\"discount\":\"normal\"}");

        auditoriumList.add("{\"name\":\"Universal\",\"vipSeats\":\"15\",\"normalSeats\":\"50\"}");
        auditoriumList.add("{\"name\":\"Illumination\",\"vipSeats\":\"0\",\"normalSeats\":\"10\"}");
        auditoriumList.add("{\"name\":\"Disney\",\"vipSeats\":\"10\",\"normalSeats\":\"40\"}");
        auditoriumList.add("{\"name\":\"Fox\",\"vipSeats\":\"5\",\"normalSeats\":\"20\"}");
    }

    private void addUsers(){
        try {
            for (String user : userList){
                userService.createUser(mapper.readValue(user, UserDTO.class));
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

    }
    private void addEvents(){
        try {
            for (String event : eventList) {
                eventService.createEvent(mapper.readValue(event, EventDTO.class));
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void addAuditoriums(){
        try {
            for (String auditorium : auditoriumList) {
                auditoriumService.createAuditorium(mapper.readValue(auditorium, AuditoriumDTO.class));
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
