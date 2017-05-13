package com.epam.www.service;

import com.epam.www.domain.DiscountEnums;
import com.epam.www.domain.Price;

import java.util.List;

/**
 * Created by Farkas on 2017.05.13..
 */
public interface PricingService {

    Price calculatePrice(int normalSeats, int vipSeats, long price, List<DiscountEnums> discounts);
}

