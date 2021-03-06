package com.epam.www.dataaccess.dao;

import com.epam.www.dataaccess.entity.Event;
import com.epam.www.dto.AuditoriumDTO;
import com.epam.www.dto.BookingDTO;
import com.epam.www.dto.EventDTO;
import com.epam.www.dto.UserDTO;

import java.net.CookieHandler;
import java.util.List;
import java.util.Map;

/**
 * Created by Farkas on 2017.03.23..
 */
public interface HibernateDaoFacade {

    EventDTO readEventsWithParams(int params);

    List<EventDTO> readEventsWithParams(final Map<String, String> params);

    UserDTO getUserById(int id);

    AuditoriumDTO readAuditoriumByName(String auditorium);

    void updateUser(UserDTO userDTO);

    Long countVipSeatsForEvent(int eventId);

    Long countNormalSeatsForEvent(int eventId);

    List<BookingDTO> readBookingsByUser(int userId);
}
