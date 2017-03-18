package com.epam.www.dataaccess.dao.impl;

import com.epam.www.dataaccess.HibernateJPA;
import com.epam.www.dataaccess.dao.BookingDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Farkas on 2017.03.15..
 */
@Repository
@Transactional
public class BookingHibernate implements BookingDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingHibernate.class);

    private HibernateJPA hibernateJPA;

    public BookingHibernate(HibernateJPA hibernateJPA){
        this.hibernateJPA = hibernateJPA;
    }


    @Override
    public int countVipSeatsForEvent(int eventId) {
        return 0;
    }

    @Override
    public int countNormalSeatsForEvent(int eventId) {
        return 0;
    }
}
