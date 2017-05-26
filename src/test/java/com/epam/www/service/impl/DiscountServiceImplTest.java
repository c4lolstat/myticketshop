package com.epam.www.service.impl;

import com.epam.www.dataaccess.dao.HibernateDaoFacade;
import com.epam.www.domain.DiscountEnums;
import com.epam.www.dto.BookingDTO;
import com.epam.www.service.DiscountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;


/**
 * Created by Farkas on 2017.05.18..
 */
@RunWith(MockitoJUnitRunner.class)
public class DiscountServiceImplTest {

    @Mock
    private HibernateDaoFacade hibernateDaoFacade;

    @InjectMocks
    private DiscountService discountService = new DiscountServiceImpl();

    private List<BookingDTO> bookingList;

    @Before
    public void setup(){
        bookingList = new ArrayList<>();
    }

    @Test
    public void givenBookingListWithNineElementWhenCheckingDiscountThenTenthIsIssued(){
        for(int i=0;i<9;i++){
            BookingDTO booking = new BookingDTO.BookingBuilder().build();
            bookingList.add(booking);
        }
        Mockito.when(hibernateDaoFacade.readBookingsByUser(anyInt())).thenReturn(bookingList);
        List<DiscountEnums> result = discountService.getDiscountForUser(anyInt());
        assertEquals(DiscountEnums.EVERY_TEN_BOOKING,result.get(1));
    }

    @Test
         public void givenBookingListWith6000WhenCheckingDiscountThenFivePercentIsIssued(){
        BookingDTO booking = new BookingDTO.BookingBuilder().withSumPrice(6000L).build();
        bookingList.add(booking);
        Mockito.when(hibernateDaoFacade.readBookingsByUser(anyInt())).thenReturn(bookingList);
        List<DiscountEnums> result = discountService.getDiscountForUser(anyInt());
        assertEquals(DiscountEnums.FIVE_PERCENT,result.get(0));
    }

    @Test
    public void givenBookingListWith13000WhenCheckingDiscountThenTenPercentIsIssued(){
        BookingDTO booking = new BookingDTO.BookingBuilder().withSumPrice(13000L).build();
        bookingList.add(booking);
        Mockito.when(hibernateDaoFacade.readBookingsByUser(anyInt())).thenReturn(bookingList);
        List<DiscountEnums> result = discountService.getDiscountForUser(anyInt());
        assertEquals(DiscountEnums.TEN_PERCENT,result.get(0));
    }

    @Test
    public void givenBookingListWith18000WhenCheckingDiscountThenFifteenPercentIsIssued(){
        BookingDTO booking = new BookingDTO.BookingBuilder().withSumPrice(18000L).build();
        bookingList.add(booking);
        Mockito.when(hibernateDaoFacade.readBookingsByUser(anyInt())).thenReturn(bookingList);
        List<DiscountEnums> result = discountService.getDiscountForUser(anyInt());
        assertEquals(DiscountEnums.FIFTEEN_PERCENT,result.get(0));
    }

    @Test
    public void givenBookingListWithoutPriceWhenCheckingDiscountThenEmptyIsIssued(){
        BookingDTO booking = new BookingDTO.BookingBuilder().build();
        bookingList.add(booking);
        Mockito.when(hibernateDaoFacade.readBookingsByUser(anyInt())).thenReturn(bookingList);
        List<DiscountEnums> result = discountService.getDiscountForUser(anyInt());
        assertEquals(DiscountEnums.EMPTY,result.get(0));
    }

}
