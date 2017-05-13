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
            Long vipSeats = this.hibernateDaoFacade.countVipSeatsForEvent(eventDTO.getId());
            Long normalSeats = this.hibernateDaoFacade.countNormalSeatsForEvent(eventDTO.getId());

            EventStatList.add(new EventStats(eventDTO, normalSeats, vipSeats));
        }
        return EventStatList;
    }

    public class EventStats {
        private String title = "";
        private long queryCount;
        private Long normalSeats;
        private Long vipSeats;

        public EventStats(EventDTO eventDTO, Long normalSeats, Long vipSeats){
            this.title = eventDTO.getTitle();
            this.queryCount = eventDTO.getCounter();
            this.normalSeats = normalSeats;
            this.vipSeats = vipSeats;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Long getNormalSeats() {
            return normalSeats;
        }

        public void setNormalSeats(Long normalSeats) {
            this.normalSeats = normalSeats;
        }

        public Long getVipseats() {
            return vipSeats;
        }

        public void setVipSeats(Long vipSeats) {
            this.vipSeats = vipSeats;
        }

        public long getQueryCount() {
            return queryCount;
        }

        public void setQueryCount(long queryCount) {
            this.queryCount = queryCount;
        }
    }
}
