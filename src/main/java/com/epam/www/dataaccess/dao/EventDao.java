package com.epam.www.dataaccess.dao;

import com.epam.www.dataaccess.entity.Event;
import com.epam.www.dto.EventDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by Farkas on 2017.02.11..
 */
public interface EventDao {

    void createEvent(EventDTO eventDTO);

    void updateEvent(EventDTO eventDTO);

    void deleteEvent(final int id);

    List<Event> readEventsWithParams(final Map<String, String> params);
}
