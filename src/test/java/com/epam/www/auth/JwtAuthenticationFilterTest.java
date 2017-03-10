package com.epam.www.auth;

import com.epam.www.auth.model.JwtAuthenticationToken;
import io.jsonwebtoken.UnsupportedJwtException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;

/**
 * Created by Farkas on 2017.03.09..
 */
@RunWith(MockitoJUnitRunner.class)
public class JwtAuthenticationFilterTest {

  @Mock
  private HttpServletRequest httpServletRequest;

  @Mock
  private HttpServletResponse httpServletResponse;

  @Mock
  private AuthenticationManager authenticationManager;

  public JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();

  @Test
  public void whenCallingRequireAuthenticationThenReturnTrue(){
    boolean result = jwtAuthenticationFilter.requiresAuthentication(httpServletRequest, httpServletResponse);
    assertTrue(result);
  }

  @Test
  public void  givenRequestWithAuthHeaderWhenAttemptAuthenticationThenAuthenticateMethodCalled(){
    Mockito.when(httpServletRequest.getHeader("Authorization")).thenReturn("jwt somejwttocken");
    jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
    jwtAuthenticationFilter.attemptAuthentication(httpServletRequest, httpServletResponse);
    Mockito.verify(authenticationManager).authenticate(any(JwtAuthenticationToken.class));
  }

  @Test (expected = UnsupportedJwtException.class)
  public void givenRequestWithNullAuthHeaderWhenAttemptAuthenticateThenExceptionThown(){
    Mockito.when(httpServletRequest.getHeader("Authorization")).thenReturn(null);
    jwtAuthenticationFilter.attemptAuthentication(httpServletRequest, httpServletResponse);
  }

  @Test (expected = UnsupportedJwtException.class)
  public void givenRequestWithMalformedAuthHeaderWhenAttemptAuthenticateThenExceptionThown(){
    Mockito.when(httpServletRequest.getHeader("Authorization")).thenReturn("Bearer sometocken");
    jwtAuthenticationFilter.attemptAuthentication(httpServletRequest, httpServletResponse);
  }
}
