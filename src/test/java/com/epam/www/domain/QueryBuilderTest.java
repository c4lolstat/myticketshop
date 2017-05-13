package com.epam.www.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.Assert.assertEquals;

/**
 * Created by Farkas on 2017.04.01..
 */
public class QueryBuilderTest {

    private QueryBuilder queryBuilder;

    @Before
    public void setup(){
        queryBuilder = new QueryBuilder()
        .withBaseString("FROM Event WHERE");
    }

    @Test
    public void WhenBuildWithIdThenValidQueryIsCreated(){
        queryBuilder.withId("1");
        String result = queryBuilder.build();
        assertEquals("FROM Event WHERE id=1", result);
    }

    @Test
    public void WhenBuildQueryWithDateRangeThenValidQueryIsCreated(){
        queryBuilder.withDateRange("123456789", "987654321");
        String result = queryBuilder.build();
        assertEquals("FROM Event WHERE airDate BETWEEN 123456789 AND 987654321", result);
    }

    @Test
    public void WhenBuildQueryWithTitleThenValidQueryIsCreated(){
        queryBuilder.withTitle("testTitle");
        String result = queryBuilder.build();
        assertEquals("FROM Event WHERE title='testTitle'", result);
    }

    @Test
    public void WhenBuildQueryWithEmailThenValidQueryIsCreated(){
        queryBuilder.withEmail("testEmail");
        String result = queryBuilder.build();
        assertEquals("FROM Event WHERE email='testEmail'", result);
    }

    @Test
    public void WhenBuildQueryWithNameThenValidQueryIsCreated(){
        queryBuilder.withName("testName");
        String result = queryBuilder.build();
        assertEquals("FROM Event WHERE name='testName'", result);
    }

    @Test
    public void WhenBuildQueryWithActiveFlagThenValidQueryIsCreated(){
        queryBuilder.withActive("true");
        String result = queryBuilder.build();
        assertEquals("FROM Event WHERE active=true", result);
    }

    @Test
    public void WhenBuildQueryWithUserThenValidQueryIsCreated(){
        queryBuilder.withUser(1);
        String result = queryBuilder.build();
        assertEquals("FROM Event WHERE user=1", result);
    }
}
