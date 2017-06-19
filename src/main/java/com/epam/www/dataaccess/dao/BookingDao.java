package com.epam.www.dataaccess.dao;

import com.epam.www.dataaccess.entity.Booking;
import com.epam.www.dto.BookingDTO;

import java.util.List;

/**
 * Created by Farkas on 2017.03.15..
 */
public interface BookingDao {
    Long countVipSeatsForEvent(int eventId);

    Long countNormalSeatsForEvent(int eventId);

    void createBooking(BookingDTO booking);

    List<Booking> readBookingsByUser(int userId);
}
