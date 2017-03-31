package com.epam.www.dataaccess.dao.impl;

import com.epam.www.dataaccess.HibernateJPA;
import com.epam.www.dataaccess.dao.EventDao;
import com.epam.www.dataaccess.entity.Event;
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
        this.hibernateJPA.getEntityManager().remove(event);
    }

    @Override
    public List<Event> readEventsWithParams(Map<String, String> params) {
      String query = buildQueryFromParamList(params);
      List<Event> eventRecord = this.hibernateJPA.getEntityManager().createQuery(query,Event.class).getResultList();
      return eventRecord;
    }

    private Event readEventById(int id) {
        String query = "FROM Event e WHERE e.id=?1";
        List<Event> eventRecord = this.hibernateJPA.getEntityManager().createQuery(query, Event.class).setParameter(1, id).getResultList();
        return eventRecord.get(0);
    }

    //TODO convert it to builder pattern to move the ifs in methods.
    //TODO make all changes in different branch
    String buildQueryFromParamList(Map<String,String> params){
        StringBuilder builder = new StringBuilder(BASE_QUERY);
        Set<String> keys = params.keySet();
        LocalDate today = LocalDate.now();
        LocalDate fiveDaysFromNow = today.plusDays(5);
            if (keys.contains("id")){
                builder.append(" id=");
                builder.append(params.get("id"));
            }
            if(params.isEmpty()){
                builder.append(" airDate BETWEEN ");
                builder.append(today.atStartOfDay().toEpochSecond(ZoneOffset.UTC));
                builder.append(" AND ");
                builder.append(fiveDaysFromNow.atStartOfDay().toEpochSecond(ZoneOffset.UTC));
            }
            if (keys.contains("title")) {
                builder.append(" title='");
                builder.append(params.get("title"));
                builder.append("'");
            }
            if(keys.contains("date")){
                Long epochTime = Long.parseLong(params.get("date"));
                LocalDateTime date = LocalDateTime.ofEpochSecond(epochTime, 1000, ZoneOffset.UTC);
                long startOfDayInSeconds = date.toLocalDate().atStartOfDay().toEpochSecond(ZoneOffset.UTC);
                long endOfDayInSeconds = startOfDayInSeconds + 24*60*60;
                builder.append(" airDate BETWEEN ");
                builder.append(startOfDayInSeconds);
                builder.append(" AND ");
                builder.append(endOfDayInSeconds);
            }
            return builder.toString();

    }


    private void update(Event event, EventDTO eventDTO){
          event.setAuditorium(eventDTO.getAuditorium());
          event.setCounter(eventDTO.getCounter());
          event.setAirDate(eventDTO.getAirDate());
          event.setPrice(eventDTO.getPrice());
          event.setTitle(eventDTO.getTitle());
      }
}
