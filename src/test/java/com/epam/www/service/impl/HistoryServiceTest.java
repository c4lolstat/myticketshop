package com.epam.www.service.impl;

import com.epam.www.auth.JwtUtil;
import com.epam.www.dataaccess.dao.HibernateDaoFacade;
import com.epam.www.dto.BookingDTO;
import com.epam.www.dto.UserDTO;
import com.epam.www.service.HistoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

/**
 * Created by Farkas on 2017.04.08..
 */
@RunWith(MockitoJUnitRunner.class)
public class HistoryServiceTest {

    private List<BookingDTO> bookingList = Collections.emptyList();
    private UserDTO userDTO;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private HibernateDaoFacade hibernateDaoFacade;

    @InjectMocks
    private HistoryService historyService = new HistoryServiceImpl();

    @Before
    public void setup(){
        userDTO = new UserDTO();
        userDTO.setId(1);
    }

    @Test
    public void whenHistoryCalledThenBookingListReturned(){
        Mockito.when(jwtUtil.getTokenFromRequest(any(HttpServletRequest.class))).thenReturn("sometokken");
        Mockito.when(jwtUtil.parseToken(anyString())).thenReturn(userDTO);
        Mockito.when(hibernateDaoFacade.readBookingsByUser(anyInt())).thenReturn(bookingList);
        List<BookingDTO> result =  this.historyService.getUserBookingHistory(any(HttpServletRequest.class));
        assertEquals(Collections.emptyList(),result);
    }


}
