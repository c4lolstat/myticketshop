package com.epam.www.service.impl;

import com.epam.www.domain.DiscountEnums;
import com.epam.www.domain.Price;
import com.epam.www.service.PricingService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Farkas on 2017.05.13..
 */
@Component
public class PricingServiceImpl implements PricingService {

    public Price calculatePrice(int normalSeats, int vipSeats, long price, List<DiscountEnums> discounts) {

        return null;
    }
}
