package com.epam.www.domain;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Farkas on 2017.06.19..
 */
@Aspect
@Component
public class DiscountCounterAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscountCounterAspect.class);
    private long issuedDiscounts = 0;

    @AfterReturning(
            pointcut = "issueDiscounts()",
            returning = "discountList")
    public void countEventQuery(final JoinPoint joinPoint, List<DiscountEnums> discountList){
        for (DiscountEnums discount : discountList ){
            if (discount != DiscountEnums.EMPTY){
                issuedDiscounts++;
            }
        }
        LOGGER.debug("total number of discount issued: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + issuedDiscounts );

    }

    @Pointcut("execution(* com.epam.www.service.impl.DiscountServiceImpl.getDiscountForUser(*))")
    private void issueDiscounts(){}
}
