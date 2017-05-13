package com.epam.www.service.impl;

import com.epam.www.dataaccess.dao.HibernateDaoFacade;
import com.epam.www.dto.EventDTO;
import com.epam.www.service.StatService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyMap;

/**
 * Created by Farkas on 2017.04.08..
 */
@RunWith(MockitoJUnitRunner.class)
public class StatServiceTest {

    private List<EventDTO> eventDTOList = new ArrayList<>();

    @Mock
    private HibernateDaoFacade hibernateDaoFacade;

    @InjectMocks
    private StatService statService = new StatServiceImpl();

    @Before
    public void setup(){
        EventDTO eventDTO = new EventDTO();
        eventDTO.setTitle("testmovie");
        eventDTO.setCounter(10);
        this.eventDTOList.add(eventDTO);
    }

    @Test
    public void whenEventInfoCalledThenEventStatsAreReturned(){
        Mockito.when(hibernateDaoFacade.readEventsWithParams(anyMap())).thenReturn(eventDTOList);
        Mockito.when(hibernateDaoFacade.countVipSeatsForEvent(anyInt())).thenReturn(5L);
        Mockito.when(hibernateDaoFacade.countNormalSeatsForEvent(anyInt())).thenReturn(7L);
        List<StatServiceImpl.EventStats> result = this.statService.getEventInfoByParams(anyMap());
        assertEquals("testmovie",result.get(0).getTitle());
        assertEquals(10, result.get(0).getQueryCount());
        assertEquals(5L, (long) result.get(0).getVipseats());
        assertEquals(7L, (long) result.get(0).getNormalSeats());
    }
}
