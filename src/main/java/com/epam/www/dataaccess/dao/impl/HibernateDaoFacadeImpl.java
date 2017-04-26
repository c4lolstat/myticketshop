package com.epam.www.dataaccess.dao.impl;

import com.epam.www.dataaccess.dao.*;
import com.epam.www.dataaccess.entity.Auditorium;
import com.epam.www.dataaccess.entity.Event;
import com.epam.www.dataaccess.entity.User;
import com.epam.www.dto.AuditoriumDTO;
import com.epam.www.dto.EventDTO;
import com.epam.www.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Farkas on 2017.03.23..
 */
@Component
public class HibernateDaoFacadeImpl implements HibernateDaoFacade {

    @Autowired
    private EventDao eventDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuditoriumDao auditoriumDao;

    @Autowired
    private BookingDao bookingDao;


    @Override
    public EventDTO readEventsWithParams(int id) {
        Map<String,String> params = new HashMap<>();
        params.put("id",Integer.valueOf(id).toString());
        Event event = this.eventDao.readEventsWithParams(params).get(0);
       return new EventDTO(event);
    }

    @Override
    public List<EventDTO> readEventsWithParams(Map<String, String> params) {
        List<Event> events = this.eventDao.readEventsWithParams(params);
        List<EventDTO> eventDTOList = new ArrayList<>();
        for (Event event : events){
            eventDTOList.add(new EventDTO(event));
        }
         return eventDTOList;
    }

    @Override
    public UserDTO getUserById(int id) {
        User user = this.userDao.getUserById(id);
       return new UserDTO(user);
    }

    @Override
    public AuditoriumDTO readAuditoriumByName(String name) {
        Auditorium auditorium = this.auditoriumDao.readAuditoriumByName(name);
        return new AuditoriumDTO(auditorium);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        this.userDao.updateUser(userDTO);
    }

    @Override
    public Long countVipSeatsForEvent(int eventId) {
        return this.bookingDao.countVipSeatsForEvent(eventId);
    }

    @Override
    public Long countNormalSeatsForEvent(int eventId) {
        return this.bookingDao.countNormalSeatsForEvent(eventId);
    }

    @Override
    public List readBookingsByUser(int userId) {
        return this.bookingDao.readBookingsByUser(userId);
    }
}
