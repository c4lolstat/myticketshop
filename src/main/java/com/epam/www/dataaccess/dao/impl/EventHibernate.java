package com.epam.www.dataaccess.dao.impl;

import com.epam.www.dataaccess.dao.EventDao;
import com.epam.www.dataaccess.entity.Event;
import com.epam.www.domain.QueryBuilder;
import com.epam.www.dto.EventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Farkas on 2017.02.23..
 */
@Repository
public class EventHibernate implements EventDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventHibernate.class);

    private final String BASE_QUERY = "FROM Event WHERE";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void createEvent(EventDTO eventDTO) {
        Event event = new Event();
        this.update(event, eventDTO);
        entityManager.persist(event);
    }

    @Override
    @Transactional
    public void updateEvent(EventDTO eventDTO) {
        Event event = this.readEventById(eventDTO.getId());
        this.update(event, eventDTO);
        entityManager.flush();
    }

    @Transactional
    public void flushEvent() {
        entityManager.flush();
    }

    @Override
    @Transactional
    public void deleteEvent(int id) {
        Event event = this.readEventById(id);
        event.setActive(false);
        entityManager.flush();
    }

    @Override
    @Transactional
    public List<Event> readEventsWithParams(Map<String, String> params) {
      String query = buildQueryFromParamList(params);
      List<Event> eventRecord = entityManager.createQuery(query, Event.class).getResultList();
      return eventRecord;
    }

    private Event readEventById(int id) {
        String query = new QueryBuilder()
                .withBaseString(BASE_QUERY)
                .withId(Integer.valueOf(id).toString())
                .build();
        List<Event> eventRecord = entityManager.createQuery(query, Event.class).getResultList();
        return eventRecord.get(0);
    }

    private String buildQueryFromParamList(Map<String,String> params){
        QueryBuilder queryBuilder = new QueryBuilder().withBaseString(BASE_QUERY);
        Set<String> keys = params.keySet();
        if(keys.isEmpty()){
            queryBuilder.withActive("true");
        }
        if(keys.contains("id")){
            queryBuilder.withId(params.get("id"));
        }
        if(keys.contains("title")) {
            queryBuilder.withTitle(params.get("title"));

        }
        if(keys.contains("active")) {
            queryBuilder.withActive(params.get("active"));

        }
        if(keys.contains("from") && keys.contains("to")){
            queryBuilder.withDateRange(params.get("from"), params.get("to"));
        }
        return queryBuilder.build();
    }

    private void update(Event event, EventDTO eventDTO){
          event.setAuditorium(eventDTO.getAuditorium());
          event.setCounter(eventDTO.getCounter());
          event.setAirDate(eventDTO.getAirDate());
          event.setPrice(eventDTO.getPrice());
          event.setTitle(eventDTO.getTitle());
          event.setActive(eventDTO.isActive());
      }
}
