package com.epam.www.dataaccess.dao.impl;

import com.epam.www.dataaccess.HibernateJPA;
import com.epam.www.dataaccess.dao.EventDao;
import com.epam.www.dataaccess.entity.Event;
import com.epam.www.dataaccess.entity.User;
import com.epam.www.dto.EventDTO;
import com.epam.www.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Farkas on 2017.02.23..
 */
@Repository
@Transactional
public class EventHibernate implements EventDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventHibernate.class);

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
        Event event = this.readEventById(eventDTO.getId());
        this.update(event, eventDTO);
        this.hibernateJPA.getEntityManager().flush();
    }

    @Override
    public void deleteEvent(int id) {
        Event event = this.readEventById(id);
        this.hibernateJPA.getEntityManager().remove(event);
    }

    @Override
    public Event readEventById(int id) {
        String query = "FROM Event e WHERE e.id=?1";
        List<Event> eventRecord = this.hibernateJPA.getEntityManager().createQuery(query, Event.class).setParameter(1, id).getResultList();
        return eventRecord.get(0);
    }

    private void update(Event event, EventDTO eventDTO){
        event.setAuditorium(eventDTO.getAuditorium());
        event.setCounter(eventDTO.getCounter());
        event.setEndDate(eventDTO.getEndDate());
        event.setHour(eventDTO.getHour());
        event.setPrice(eventDTO.getPrice());
        event.setStartDate(eventDTO.getStartDate());
        event.setTitle(eventDTO.getTitle());
    }
}
