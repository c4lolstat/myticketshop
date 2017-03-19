package com.epam.www.dataaccess.dao.impl;

import com.epam.www.dataaccess.HibernateJPA;
import com.epam.www.dataaccess.dao.BookingDao;
import com.epam.www.dataaccess.entity.Booking;
import com.epam.www.dataaccess.entity.Event;
import com.epam.www.dto.BookingDTO;
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

    @Override
    public void createBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        this.update(booking, bookingDTO);
        this.hibernateJPA.getEntityManager().persist(booking);
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
