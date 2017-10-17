package com.epam.www.dataaccess.dao.impl;

import com.epam.www.dataaccess.dao.BookingDao;
import com.epam.www.dataaccess.entity.Booking;
import com.epam.www.domain.QueryBuilder;
import com.epam.www.dto.BookingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Farkas on 2017.03.15..
 */
@Repository
public class BookingHibernate implements BookingDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingHibernate.class);
    private final String BASE_QUERY = "FROM Booking WHERE";

    @PersistenceContext
    private EntityManager entityManager;

    //TODO handle Long somehow better
    @Override
    @Transactional
    public Long countVipSeatsForEvent(int eventId) {
        String query = new QueryBuilder()
                .withBaseString("SELECT SUM (vipSeats) " + BASE_QUERY)
                .withEventId(eventId)
                .build();
        return (Long) entityManager.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional
    public Long countNormalSeatsForEvent(int eventId) {
        String query = new QueryBuilder()
                .withBaseString("SELECT SUM (normalSeats) " + BASE_QUERY)
                .withEventId(eventId)
                .build();
        return (Long) entityManager.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional
    public void createBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        this.update(booking, bookingDTO);
        entityManager.persist(booking);
    }

    @Override
    @Transactional
    public List readBookingsByUser(int userId) {
        String query = new QueryBuilder().withBaseString(BASE_QUERY)
                .withUser(userId)
                .build();
        return entityManager.createQuery(query).getResultList();
    }

    private void update(Booking booking, BookingDTO bookingDTO){
        booking.setUser(bookingDTO.getUser());
        booking.setEvent(bookingDTO.getEvent());
        booking.setNormalSeats(bookingDTO.getNormalSeats());
        booking.setVipSeats(bookingDTO.getVipSeats());
        booking.setSumPrice(bookingDTO.getSumPrice());
        booking.setBooked(bookingDTO.isBooked());
        booking.setPayed(bookingDTO.isPayed());
        booking.setDiscount(bookingDTO.getDiscount());
    }
}
