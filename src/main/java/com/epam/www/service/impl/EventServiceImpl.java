package com.epam.www.service.impl;

import com.epam.www.dataaccess.dao.EventDao;
import com.epam.www.dataaccess.entity.Event;
import com.epam.www.dto.EventDTO;
import com.epam.www.dto.UserDTO;
import com.epam.www.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Farkas on 2017.02.23..
 */

@Component
public class EventServiceImpl implements EventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

    @Autowired
    private EventDao eventDao;

    @Override
    public void createEvent(EventDTO eventDTO) {
        eventDao.createEvent(eventDTO);
    }

    @Override
    public void updateEvent(EventDTO eventDTO) {
        eventDao.updateEvent(eventDTO);
    }

    @Override
    public void deleteEvent(int id) {
        eventDao.deleteEvent(id);
    }

    @Override
    public List<EventDTO> readEventsWithParams(Map<String, String> params) {
        List<Event> events = eventDao.readEventsWithParams(params);
        return detachEventResults(events);
    }

    private List<EventDTO> detachEventResults(List<Event> events){
      List<EventDTO> eventDTOList = new ArrayList<>();
      for (Event event : events){
        eventDTOList.add(new EventDTO(event));
      }
      return eventDTOList;
    }
}
