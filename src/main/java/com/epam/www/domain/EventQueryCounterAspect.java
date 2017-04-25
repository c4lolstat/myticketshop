package com.epam.www.domain;

import com.epam.www.dataaccess.dao.impl.EventHibernate;
import com.epam.www.dataaccess.entity.Event;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Farkas on 2017.04.09..
 */
@Aspect
@Component
public class EventQueryCounterAspect {

    @AfterReturning(
            pointcut = "executeReadQuery()",
            returning = "eventRecord")
    public void countEventQuery(final JoinPoint joinPoint, List<Event> eventRecord){
        System.out.println("some aspect should run");
        EventHibernate eventHibernate = (EventHibernate) joinPoint.getTarget();
        for (Event event : eventRecord){
            event.setCounter(event.getCounter() + 1);
            eventHibernate.flushEvent();
        }
    }

    @Pointcut("execution(* com.epam.www.dataaccess.dao.impl.EventHibernate.readEventsWithParams(*))")
    private void executeReadQuery(){}
}
