package com.epam.www.dataaccess.dao;

/**
 * Created by Farkas on 2017.03.15..
 */
public interface BookingDao {
    int countVipSeatsForEvent(int eventId);

    int countNormalSeatsForEvent(int eventId);

}
