package com.epam.www.service.impl;

import com.epam.www.dataaccess.dao.EventDao;
import com.epam.www.dataaccess.entity.Event;
import com.epam.www.dto.EventDTO;
import com.epam.www.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Farkas on 2017.02.23..
 */

@Component
public class EventService implements IEventService {

    @Autowired
    private EventDao eventDao;

    @Override
    public void createEvent(EventDTO eventDTO) {
        eventDao.createEvent(eventDTO);
    }

    @Override
    public void updateEvent(EventDTO eventDTO) {

    }

    @Override
    public void deleteEvent(long hash) {

    }

    @Override
    public Event readEvent(long hash) {
        return null;
    }
}
