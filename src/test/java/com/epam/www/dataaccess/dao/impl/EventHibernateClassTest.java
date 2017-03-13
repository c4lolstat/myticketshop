package com.epam.www.dataaccess.dao.impl;

import com.epam.www.dataaccess.HibernateJPA;
import org.h2.command.dml.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Farkas on 2017.03.11..
 */
@RunWith(MockitoJUnitRunner.class)
public class EventHibernateClassTest {

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
    public void givenParamsWithOnlyStratDateWhenBuildQueryThenValidQueryIsCreated(){
        params.put("startDate","666");
        params.put("endDate","1000");
        String result = eventHibernate.buildQueryFromParamList(params);
        assertEquals("FROM Event WHERE startDate>=666 AND endDate<=1000", result);
    }
}
