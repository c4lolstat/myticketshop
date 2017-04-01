package com.epam.www.dataaccess.dao.impl;

import com.epam.www.dataaccess.HibernateJPA;
import com.epam.www.dataaccess.dao.EventDao;
import com.epam.www.dataaccess.entity.Event;
import com.epam.www.domain.QueryBuilder;
import com.epam.www.dto.EventDTO;
import net.sf.cglib.core.Local;
import org.h2.command.dml.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Farkas on 2017.02.23..
 */
@Repository
@Transactional
public class EventHibernate implements EventDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventHibernate.class);

    private final String BASE_QUERY = "FROM Event WHERE";

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
        event.setActive(false);
        this.hibernateJPA.getEntityManager().flush();
    }

    @Override
    public List<Event> readEventsWithParams(Map<String, String> params) {
      String query = buildQueryFromParamList(params);
      List<Event> eventRecord = this.hibernateJPA.getEntityManager().createQuery(query,Event.class).getResultList();
      return eventRecord;
    }

    private Event readEventById(int id) {
        String query = new QueryBuilder()
                .withBaseString(BASE_QUERY)
                .withId(Integer.valueOf(id).toString())
                .build();
        List<Event> eventRecord = this.hibernateJPA.getEntityManager().createQuery(query, Event.class).setParameter(1, id).getResultList();
        return eventRecord.get(0);
    }

    private String buildQueryFromParamList(Map<String,String> params){
        QueryBuilder queryBuilder = new QueryBuilder().withBaseString(BASE_QUERY);
        Set<String> keys = params.keySet();
        long today = LocalDate.now().atStartOfDay().toEpochSecond(ZoneOffset.UTC);
        long fiveDaysFromNow = LocalDate.now().plusDays(5).atStartOfDay().toEpochSecond(ZoneOffset.UTC);
            if (keys.contains("id")){
                queryBuilder.withId(params.get("id"));
            }
            if(params.isEmpty()){
                queryBuilder.withDateRange(Long.valueOf(today).toString(), Long.valueOf(fiveDaysFromNow).toString());
            }
            if (keys.contains("title")) {
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
