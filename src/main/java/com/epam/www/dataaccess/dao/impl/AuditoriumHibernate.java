package com.epam.www.dataaccess.dao.impl;

import com.epam.www.dataaccess.HibernateJPA;
import com.epam.www.dataaccess.dao.AuditoriumDao;
import com.epam.www.dataaccess.entity.Auditorium;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Farkas on 2017.03.15..
 */
@Repository
@Transactional
public class AuditoriumHibernate implements AuditoriumDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditoriumHibernate.class);

    private HibernateJPA hibernateJPA;

    public AuditoriumHibernate (HibernateJPA hibernateJPA){
        this.hibernateJPA = hibernateJPA;
    }
}
