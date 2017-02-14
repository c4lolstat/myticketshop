package com.epam.www.dataaccess;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Farkas on 2017.02.07..
 */
public class HibernateJPA {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager (){
        return this.entityManager;
    }



}
