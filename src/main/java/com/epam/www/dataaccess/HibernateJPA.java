package com.epam.www.dataaccess;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

/**
 * Created by Farkas on 2017.02.07..
 */
public class HibernateJPA {

    @PersistenceContext
    private EntityManagerFactory entityManagerFactory;

    public EntityManager getEntityManager (){
        System.out.println(entityManagerFactory.toString());
        return this.entityManagerFactory.createEntityManager();
    }



}
