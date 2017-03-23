package com.epam.www.auth;

import io.jsonwebtoken.UnsupportedJwtException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;

/**
 * Created by Farkas on 2017.03.23..
 */
@RunWith(MockitoJUnitRunner.class)
public class JwtUtilTest {

    @Mock
    private HttpServletRequest request;

    private JwtUtil jwtUtil = new JwtUtil();

    @Before
    public void setup(){}

    @Test
    public void givenRequestWithAuthHeaderWhenGetTokenFromRequestThenTokenReturned(){
        Mockito.when(request.getHeader("Authorization")).thenReturn("jwt correctHeader");
        String result = jwtUtil.getTokenFromRequest(request);
        assertEquals("correctHeader",result);
    }

    @Test(expected = UnsupportedJwtException.class)
    public void givenRequestWithNullAuthHeaderWhenGetTokenFromRequestThenExceptionThorws(){
        Mockito.when(request.getHeader("Authorization")).thenReturn(null);
        jwtUtil.getTokenFromRequest(request);
    }

    @Test(expected = UnsupportedJwtException.class)
    public void givenRequestWithWrongAuthHeaderWhenGetTokenFromRequestThenExceptionThrows(){
        Mockito.when(request.getHeader("Authorization")).thenReturn("bearer correctHeader");
        jwtUtil.getTokenFromRequest(request);
    }
}
