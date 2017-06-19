package com.epam.www.service.impl;

import com.epam.www.domain.DiscountEnums;
import com.epam.www.domain.Price;
import com.epam.www.service.PricingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Farkas on 2017.05.18..
 */
@RunWith(MockitoJUnitRunner.class)
public class PricingServiceImplTest {

    private static final BigDecimal BASE_PRICE = BigDecimal.valueOf(100.0D);
    private static final BigDecimal TEN_PERCENT = BigDecimal.valueOf(90.0D);
    private static final BigDecimal FIVE_PERCENT = BigDecimal.valueOf(95.0D);
    private static final BigDecimal HALF_PRICE = BigDecimal.valueOf(50.0D);
    private static final BigDecimal MULTIPLE = BigDecimal.valueOf(45.0D);
    private static final BigDecimal FIFTEEN_PERCENT = BigDecimal.valueOf(85.0D);
    private PricingService pricingService = new PricingServiceImpl();

    private List<DiscountEnums> discountList;

    @Before
    public void setup(){
        discountList = new ArrayList<>();
    }

    @Test
    public void givenTenthDiscountWhenCalculatingPriceThenValueIsCorrect(){
        discountList.add(DiscountEnums.EVERY_TEN_BOOKING);
        Price result = pricingService.getPrice(1,0, BASE_PRICE,discountList);
        assertTrue(HALF_PRICE.compareTo(result.getSumPrice()) == 0);
    }

    @Test
    public void givenFivePercentDiscountWhenCalculatingPriceThenValueIsCorrect(){
        discountList.add(DiscountEnums.FIVE_PERCENT);
        Price result = pricingService.getPrice(1,0,BASE_PRICE,discountList);
        assertTrue(FIVE_PERCENT.compareTo(result.getSumPrice()) == 0);
    }

    @Test
    public void givenTenPercentDiscountWhenCalculatingPriceThenValueIsCorrect(){
        discountList.add(DiscountEnums.TEN_PERCENT);
        Price result = pricingService.getPrice(1,0,BASE_PRICE,discountList);
        assertTrue(TEN_PERCENT.compareTo(result.getSumPrice()) == 0);
    }

    @Test
    public void givenFifteenPercentDiscountWhenCalculatingPriceThenValueIsCorrect(){
        discountList.add(DiscountEnums.FIFTEEN_PERCENT);
        Price result = pricingService.getPrice(1,0,BASE_PRICE,discountList);
        assertTrue(FIFTEEN_PERCENT.compareTo(result.getSumPrice()) == 0);
    }

    @Test
    public void givenMultipleDiscountWhenCalculatingPriceThenValueIsCorrect(){
        discountList.add(DiscountEnums.TEN_PERCENT);
        discountList.add(DiscountEnums.EVERY_TEN_BOOKING);
        Price result = pricingService.getPrice(1,0,BASE_PRICE,discountList);
        assertTrue(MULTIPLE.compareTo(result.getSumPrice()) == 0);
    }
}
