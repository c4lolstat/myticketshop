package com.epam.www.dataaccess.dao.impl;

import com.epam.www.dataaccess.dao.AuditoriumDao;
import com.epam.www.dataaccess.dao.EventDao;
import com.epam.www.dataaccess.dao.HibernateDaoFacade;
import com.epam.www.dataaccess.dao.UserDao;
import com.epam.www.dataaccess.entity.Auditorium;
import com.epam.www.dataaccess.entity.Event;
import com.epam.www.dataaccess.entity.User;
import com.epam.www.dto.AuditoriumDTO;
import com.epam.www.dto.EventDTO;
import com.epam.www.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


    @Override
    public EventDTO readEventsWithParams(Map<String, String> params) {
        Event event = this.eventDao.readEventsWithParams(params).get(0);
       return new EventDTO(event);
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
}
