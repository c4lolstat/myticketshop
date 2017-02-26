package com.epam.www.dataaccess.dao;

import com.epam.www.dataaccess.entity.Event;
import com.epam.www.dto.EventDTO;

/**
 * Created by Farkas on 2017.02.11..
 */
public interface EventDao {

    void createEvent(EventDTO eventDTO);

    void updateEvent(EventDTO eventDTO);

    void deleteEvent(final long hash);

    Event readEvent(final long hash);
}
