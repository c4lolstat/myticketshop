package com.epam.www.dataaccess;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

/**
 * Created by Farkas on 2017.02.07..
 */
@Repository
public class HibernateJPA {

    @PersistenceContext
    private EntityManagerFactory entityManagerFactory;

//    public HibernateJPA(){
//        this.entityManager = entityManagerFactory.createEntityManager();
//    }

    public EntityManager getEntityManager (){
//        System.out.println(this.entityManagerFactory.toString());
        return this.entityManagerFactory.createEntityManager();
    }



}
