package com.epam.www.service;

import com.epam.www.dto.BookingDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Farkas on 2017.04.08..
 */
public interface HistoryService {
    List<BookingDTO> getUserBookingHistory(HttpServletRequest request);
}
