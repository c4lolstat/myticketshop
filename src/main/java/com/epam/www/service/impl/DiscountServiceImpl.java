package com.epam.www.service.impl;

import com.epam.www.dataaccess.dao.HibernateDaoFacade;
import com.epam.www.domain.DiscountEnums;
import com.epam.www.dto.BookingDTO;
import com.epam.www.dto.UserDTO;
import com.epam.www.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farkas on 2017.05.13..
 */
@Component
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private HibernateDaoFacade hibernateDaoFacade;

    private List<BookingDTO> bookingList;

    @Override
    public List<DiscountEnums> getDiscountForUser(UserDTO userDTO) {
        List<DiscountEnums> discountList = new ArrayList<>();
        bookingList = hibernateDaoFacade.readBookingsByUser(userDTO.getId());
        discountList.add(discountByMoneySpent());
        discountList.add(discountByBoughtTickets());
        return discountList;
    }

    private DiscountEnums discountByBoughtTickets() {
        int ticketsNumber = this.bookingList.size();
        return (ticketsNumber % 10) == 9 ? DiscountEnums.EVERY_TEN_TICKET : DiscountEnums.EMPTY;
    }

    private DiscountEnums discountByMoneySpent() {
        long allTheMoneySpent = 0;

        for (BookingDTO bookingDTO : bookingList){
            allTheMoneySpent += bookingDTO.getSumPrice();
        }

        if ( allTheMoneySpent >= 5000 && allTheMoneySpent < 10000) {
            return DiscountEnums.FIVE_PERCENT;
        }
        if (allTheMoneySpent >= 10000 && allTheMoneySpent < 15000) {
            return DiscountEnums.TEN_PERCENT;
        }
        if (allTheMoneySpent >= 15000 && allTheMoneySpent < 20000) {
            return DiscountEnums.FIFTEEN_PERCENT;
        }
        return DiscountEnums.EMPTY;
    }
}
