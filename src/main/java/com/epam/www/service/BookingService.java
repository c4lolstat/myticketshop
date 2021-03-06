package com.epam.www.service;

import com.epam.www.dto.AvailableSeatsDTO;
import com.epam.www.dto.BookingInfoDTO;
import com.epam.www.dto.InitBookingDTO;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Farkas on 2017.03.15..
 */
public interface BookingService {

//    AvailableSeatsDTO getAvailableSeatNumbersForEvent(Map<String, String> params);

    BookingInfoDTO getBookingInformation(int params);

    void bookTicket(InitBookingDTO params, HttpServletRequest request);

}
