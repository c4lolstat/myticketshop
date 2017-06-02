package com.epam.www.service.impl;

import com.epam.www.domain.*;
import com.epam.www.service.PricingService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Farkas on 2017.05.13..
 */
@Component
public class PricingServiceImpl implements PricingService {

    public static final BigDecimal VIP_PRICE_MODIFIER = BigDecimal.valueOf(2L);

    @Override
    public Price getPrice(int normalSeats, int vipSeats, BigDecimal basePrice, List<DiscountEnums> discounts) {

        BigDecimal initialPrice = basePrice.multiply(BigDecimal.valueOf(normalSeats))
                .add(basePrice.multiply(VIP_PRICE_MODIFIER).multiply(BigDecimal.valueOf(vipSeats)));

        Price price = new BasePrice(initialPrice);

        for (DiscountEnums discount : discounts){
            price = new DiscountPrice(price,discount);
        }

        return price;
    }
}
