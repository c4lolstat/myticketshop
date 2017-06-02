package com.epam.www.service.impl;

import com.epam.www.dataaccess.dao.HibernateDaoFacade;
import com.epam.www.domain.DiscountEnums;
import com.epam.www.dto.BookingDTO;
import com.epam.www.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farkas on 2017.05.13..
 */
@Component
public class DiscountServiceImpl implements DiscountService {

    private final BigDecimal TIER_ONE = BigDecimal.valueOf(5000);
    private final BigDecimal TIER_TWO = BigDecimal.valueOf(10000);
    private final BigDecimal TIER_THREE = BigDecimal.valueOf(15000);

    @Autowired
    private HibernateDaoFacade hibernateDaoFacade;

    private List<BookingDTO> bookingList;

    @Override
    public List<DiscountEnums> getDiscountForUser(int userId) {
        List<DiscountEnums> discountList = new ArrayList<>();
        bookingList = hibernateDaoFacade.readBookingsByUser(userId);
        discountList.add(discountByMoneySpent());
        discountList.add(discountByBoughtTickets());
        return discountList;
    }

    private DiscountEnums discountByBoughtTickets() {
        int ticketsNumber = this.bookingList.size();
        return (ticketsNumber % 10) == 9 ? DiscountEnums.EVERY_TEN_BOOKING : DiscountEnums.EMPTY;
    }

    private DiscountEnums discountByMoneySpent() {
        BigDecimal allTheMoneySpent = BigDecimal.ZERO;

        for (BookingDTO bookingDTO : bookingList){
            allTheMoneySpent = allTheMoneySpent.add(bookingDTO.getSumPrice());
        }

        if ( allTheMoneySpent.compareTo(TIER_ONE) >=  0 && allTheMoneySpent.compareTo(TIER_TWO) < 0) {
            return DiscountEnums.FIVE_PERCENT;
        }
        if (allTheMoneySpent.compareTo(TIER_TWO) >= 0&& allTheMoneySpent.compareTo(TIER_TWO) < 0) {
            return DiscountEnums.TEN_PERCENT;
        }
        if (allTheMoneySpent.compareTo(TIER_THREE) >= 0) {
            return DiscountEnums.FIFTEEN_PERCENT;
        }
        return DiscountEnums.EMPTY;
    }
}
