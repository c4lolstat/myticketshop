package com.epam.www.service;

import com.epam.www.dataaccess.entity.Event;
import com.epam.www.dto.EventDTO;

/**
 * Created by Farkas on 2017.02.23..
 */
public interface IEventService {

    void createEvent(EventDTO eventDto);

    void updateEvent(EventDTO eventDTO);

    void deleteEvent(final long hash);

    Event readEvent(final long hash);
}
