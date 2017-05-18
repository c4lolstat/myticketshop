package com.epam.www.service.impl;

import com.epam.www.domain.DiscountEnums;
import com.epam.www.domain.Price;
import com.epam.www.service.PricingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Farkas on 2017.05.18..
 */
@RunWith(MockitoJUnitRunner.class)
public class PricingServiceImplTest {

    private PricingService pricingService = new PricingServiceImpl();

    private List<DiscountEnums> discountList;

    @Before
    public void setup(){
        discountList = new ArrayList<>();
    }

    @Test
    public void givenTenthDiscountWhenCalculatingPriceThenValueIsCorrect(){
        discountList.add(DiscountEnums.EVERY_TEN_TICKET);
        Price result = pricingService.getPrice(1,0,100,discountList);
        assertEquals(50l,result.getSumPrice());
    }

    @Test
    public void givenFivePercentDiscountWhenCalculatingPriceThenValueIsCorrect(){
        discountList.add(DiscountEnums.FIVE_PERCENT);
        Price result = pricingService.getPrice(1,0,100,discountList);
        assertEquals(95l,result.getSumPrice());
    }

    @Test
    public void givenTenPercentDiscountWhenCalculatingPriceThenValueIsCorrect(){
        discountList.add(DiscountEnums.TEN_PERCENT);
        Price result = pricingService.getPrice(1,0,100,discountList);
        assertEquals(90l,result.getSumPrice());
    }

    @Test
    public void givenfifteenPercentDiscountWhenCalculatingPriceThenValueIsCorrect(){
        discountList.add(DiscountEnums.FIFTEEN_PERCENT);
        Price result = pricingService.getPrice(1,0,100,discountList);
        assertEquals(85l,result.getSumPrice());
    }
}
