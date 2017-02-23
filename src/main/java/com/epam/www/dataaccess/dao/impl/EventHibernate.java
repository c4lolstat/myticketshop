package com.epam.www.dataaccess.dao.impl;

import com.epam.www.dataaccess.HibernateJPA;
import com.epam.www.dataaccess.dao.EventDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Farkas on 2017.02.23..
 */
@Repository
@Transactional
public class EventHibernate implements EventDao {

    private HibernateJPA hibernateJPA;

    public EventHibernate(HibernateJPA hibernateJPA){
        this.hibernateJPA = hibernateJPA;
    }
}
