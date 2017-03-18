package com.epam.www.service.impl;

import com.epam.www.dataaccess.dao.AuditoriumDao;
import com.epam.www.dataaccess.entity.Auditorium;
import com.epam.www.dto.AuditoriumDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

/**
 * Created by Farkas on 2017.03.18..
 */
@RunWith(MockitoJUnitRunner.class)
public class AuditoriumServiceTest {

    private Auditorium auditorium;

    @Mock
    private AuditoriumDao auditoriumDao;

    @InjectMocks
    private AuditoriumServiceImpl auditoriumService = new AuditoriumServiceImpl();

    @Before
    public void setup(){
        auditorium = new Auditorium();
        auditorium.setId(1);
        auditorium.setName("testAuditorium");
        auditorium.setVipSeats(5);
        auditorium.setNormalSeats(10);
    }

    @Test
    public void whenAuditoriumCreatedThenDaoMethodCalled(){
        auditoriumService.createAuditorium(any(AuditoriumDTO.class));
        Mockito.verify(auditoriumDao, Mockito.times(1)).createAuditorium(any(AuditoriumDTO.class));
    }

    @Test
    public void givenAuditoriumNameWhenQueryDatabaseDtoObjectIsReturned(){
        Mockito.when(auditoriumDao.readAuditoriumByName(anyString())).thenReturn(auditorium);
        AuditoriumDTO result = auditoriumService.readAuditoriumByName("testAuditorium");
        assertEquals("testAuditorium",result.getName());
        assertEquals(5,result.getVipSeats());
        assertEquals(10,result.getNormalSeats());
    }

    @Test
    public void givenEmptyAuditoriumNameWhenWhenQueryDatabaseEmptyDtoObjectIsReturned(){
        AuditoriumDTO result = auditoriumService.readAuditoriumByName("");
        assertEquals("",result.getName());
        assertEquals(0,result.getVipSeats());
        assertEquals(0,result.getNormalSeats());
    }
}
