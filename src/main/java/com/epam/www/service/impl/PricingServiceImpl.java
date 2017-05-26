package com.epam.www.service.impl;

import com.epam.www.domain.*;
import com.epam.www.service.PricingService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Farkas on 2017.05.13..
 */
@Component
public class PricingServiceImpl implements PricingService {

    @Override
    public Price getPrice(int normalSeats, int vipSeats, long basePrice, List<DiscountEnums> discounts) {

        Price price = new BasePrice(normalSeats, vipSeats, basePrice);

        for (DiscountEnums discount : discounts){
            if(discount == DiscountEnums.EVERY_TEN_BOOKING){
                price = new TenthTicketDiscount(price);
            }
            if(discount == DiscountEnums.FIVE_PERCENT){
                price = new PercentageBasedDiscount(price, 0.95d);
            }
            if(discount == DiscountEnums.TEN_PERCENT){
                price = new PercentageBasedDiscount(price, 0.9d);
            }
            if(discount == DiscountEnums.FIFTEEN_PERCENT){
                price = new PercentageBasedDiscount(price, 0.85d);
            }
        }

        return price;
    }
}
