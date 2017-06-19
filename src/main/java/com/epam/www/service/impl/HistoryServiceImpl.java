package com.epam.www.service.impl;

import com.epam.www.auth.JwtUtil;
import com.epam.www.dataaccess.dao.HibernateDaoFacade;
import com.epam.www.dataaccess.entity.Booking;
import com.epam.www.dto.BookingDTO;
import com.epam.www.dto.UserDTO;
import com.epam.www.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farkas on 2017.04.08..
 */
@Component
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HibernateDaoFacade hibernateDaoFacade;

    public List<BookingDTO> getUserBookingHistory(HttpServletRequest request) {
        String authToken = jwtUtil.getTokenFromRequest(request);
        int userId = jwtUtil.parseToken(authToken).getId();
        return hibernateDaoFacade.readBookingsByUser(userId);
    }

}
