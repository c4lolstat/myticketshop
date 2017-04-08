package com.epam.www.service.impl;

import com.epam.www.dataaccess.dao.HibernateDaoFacade;
import com.epam.www.dto.EventDTO;
import com.epam.www.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Farkas on 2017.04.07..
 */
@Component
public class StatServiceImpl implements StatService {

    @Autowired
    private HibernateDaoFacade hibernateDaoFacade;

    @Override
    public List<EventStats> getEventInfoByParams(Map<String, String> params) {

        List<EventStats> EventStatList = new ArrayList();
        List<EventDTO> eventDTOList = this.hibernateDaoFacade.readEventsWithParams(params);

        for (EventDTO eventDTO : eventDTOList){
            int vipSeats = this.hibernateDaoFacade.countVipSeatsForEvent(eventDTO.getId());
            int normalSeats = this.hibernateDaoFacade.countNormalSeatsForEvent(eventDTO.getId());
            EventStatList.add(new EventStats(eventDTO, normalSeats, vipSeats));
        }
        return EventStatList;
    }

    public class EventStats {
        private String title = "";
        private long queryCount;
        private int normalSeats;
        private int vipseats;

        public EventStats(EventDTO eventDTO, int normalSeats, int vipSeats){
            this.title = eventDTO.getTitle();
            this.queryCount = eventDTO.getCounter();
            this.normalSeats = normalSeats;
            this.vipseats = vipSeats;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getNormalSeats() {
            return normalSeats;
        }

        public void setNormalSeats(int normalSeats) {
            this.normalSeats = normalSeats;
        }

        public int getVipseats() {
            return vipseats;
        }

        public void setVipseats(int vipseats) {
            this.vipseats = vipseats;
        }

        public long getQueryCount() {
            return queryCount;
        }

        public void setQueryCount(long queryCount) {
            this.queryCount = queryCount;
        }
    }
}
