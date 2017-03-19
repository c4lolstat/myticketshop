package com.epam.www.dataaccess.dao;

import com.epam.www.dto.BookingDTO;

/**
 * Created by Farkas on 2017.03.15..
 */
public interface BookingDao {
    int countVipSeatsForEvent(int eventId);

    int countNormalSeatsForEvent(int eventId);

    void createBooking(BookingDTO booking);

}
