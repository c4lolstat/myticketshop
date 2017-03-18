package com.epam.www.service;

import com.epam.www.dto.AvailableSeatsDTO;
import com.epam.www.dto.BookingInfoDTO;

import java.util.Map;

/**
 * Created by Farkas on 2017.03.15..
 */
public interface BookingService {

//    AvailableSeatsDTO getAvailableSeatNumbersForEvent(Map<String, String> params);

    BookingInfoDTO getBookingInformation(Map<String, String> params);
}
