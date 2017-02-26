package com.epam.www.dataaccess.dao.impl;

import com.epam.www.dataaccess.HibernateJPA;
import com.epam.www.dataaccess.dao.EventDao;
import com.epam.www.dataaccess.entity.Event;
import com.epam.www.dataaccess.entity.User;
import com.epam.www.dto.EventDTO;
import com.epam.www.dto.UserDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Farkas on 2017.02.23..
 */
@Repository
@Transactional
public class EventHibernate implements EventDao {

    private HibernateJPA hibernateJPA;

    public EventHibernate(HibernateJPA hibernateJPA){
        this.hibernateJPA = hibernateJPA;
    }

    @Override
    public void createEvent(EventDTO eventDTO) {
        Event event = new Event();
        this.update(event, eventDTO);
        this.hibernateJPA.getEntityManager().persist(event);
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

    private void update(Event event, EventDTO eventDTO){
        event.setAuditorium(eventDTO.getAuditorium());
        event.setCounter(eventDTO.getCounter());
        event.setEndDate(eventDTO.getEndDate());
        event.setHour(eventDTO.getHour());
        event.setPrice(eventDTO.getPrice());
        event.setStartDate(eventDTO.getStartDate());
        event.setTitle(eventDTO.getTitle());
        event.setHash(this.generateHash(event));
    }

    private long generateHash(Event event){
        return 42;
    }
}
