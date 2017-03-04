package com.epam.www.dataaccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Farkas on 2017.02.07..
 */
public class HibernateJPA {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateJPA.class);

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager (){
        return this.entityManager;
    }



}
