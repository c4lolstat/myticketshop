package com.epam.www.service.impl;

import com.epam.www.dataaccess.dao.EventDao;
import com.epam.www.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Farkas on 2017.02.23..
 */

@Component
public class EventService implements IEventService {

    @Autowired
    private EventDao userDao;

}
