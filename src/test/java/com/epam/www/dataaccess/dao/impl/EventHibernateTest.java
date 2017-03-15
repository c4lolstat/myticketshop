package com.epam.www.dataaccess.dao.impl;

import com.epam.www.dataaccess.HibernateJPA;
import org.h2.command.dml.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Farkas on 2017.03.11..
 */
@RunWith(MockitoJUnitRunner.class)
public class EventHibernateTest {

    @Mock
    private HibernateJPA hibernateJPA;

    private EventHibernate eventHibernate = new EventHibernate(hibernateJPA);

    private Map<String, String> params;

    @Before
    public void setup(){
        params = new HashMap<>();
    }

    @Test
    public void givenParamsWithOnlyIdWhenBuildQueryThenValidQueryIsCreated(){
        params.put("id","1");
        String result = eventHibernate.buildQueryFromParamList(params);
        assertEquals("FROM Event WHERE id=1", result);
    }

    @Test
    public void givenEmptyParamMapWhenBuildQueryThenSelectEventsFiveDaysFromNow(){
        String result = eventHibernate.buildQueryFromParamList(params);
        long today = LocalDate.now().atStartOfDay().toEpochSecond(ZoneOffset.UTC);
        long fiveDaysFromNow = LocalDate.now().plusDays(5).atStartOfDay().toEpochSecond(ZoneOffset.UTC);
        assertEquals("FROM Event WHERE airDate BETWEEN " + today + " AND " + fiveDaysFromNow, result);
    }

    @Test
    public void givenParamsWithOnlyTitleWhenBuildQueryThenValidQueryIsCreated(){
        params.put("title","testTitle");
        String result = eventHibernate.buildQueryFromParamList(params);
        assertEquals("FROM Event WHERE title='testTitle'", result);
    }

    @Test
    public void givenParamsWithOnlyDateWhenBuildQueryThenEventsForThatDaySelected(){
        long epochTime = LocalDateTime.now(ZoneOffset.UTC).toEpochSecond(ZoneOffset.UTC);
        long todayInMilli = LocalDate.now().atStartOfDay().toEpochSecond(ZoneOffset.UTC);
        long midnightInMilli = todayInMilli + 24*60*60;
        params.put("date", Long.valueOf(epochTime).toString());
        String result = eventHibernate.buildQueryFromParamList(params);
        assertEquals("FROM Event WHERE airDate BETWEEN " + todayInMilli + " AND " + midnightInMilli, result);
    }
}
