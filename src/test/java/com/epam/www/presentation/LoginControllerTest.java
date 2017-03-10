package com.epam.www.presentation;

import com.epam.www.dto.CredentialDTO;
import com.epam.www.dto.UserDTO;
import com.epam.www.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;

/**
 * Created by Farkas on 2017.03.03..
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    private CredentialDTO credentialDTO;

    @Mock
    private UserService userService;

    @InjectMocks
    private LoginController loginController = new LoginController();

    @Before
    public void setup(){
        credentialDTO = new CredentialDTO();
        credentialDTO.setEmail("sometest@gmail.com");
        credentialDTO.setPassword("1234");
    }

    @Test
    public void givenProperCredentialsWhenUserLoginThenResponseCorrect(){
        Mockito.when(userService.authenticateUser(credentialDTO)).thenReturn("jwttoken");
        ResponseEntity<String> result = loginController.login(credentialDTO);
        String resultHeaderAuthorization = result.getHeaders().get("Authorization").get(0);
        assertEquals("login attempt",result.getBody());
        assertEquals("jwttoken",resultHeaderAuthorization);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenProperCredentialsWhenUserLoginThenServiceMethodCalled(){
        Mockito.when(userService.authenticateUser(credentialDTO)).thenReturn("jwttoken");
        ResponseEntity<String> result = loginController.login(credentialDTO);
        Mockito.verify(userService, Mockito.times(1)).authenticateUser(credentialDTO);
    }

    @Test
    public void givenEmptyEmailWhenUserLoginThenResponseIsBadRequest(){
        credentialDTO.setEmail("");
        ResponseEntity<String> result = loginController.login(credentialDTO);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void givenEmptyPasswordWhenUserLoginThenResponseIsBadRequest(){
        credentialDTO.setPassword("");
        ResponseEntity<String> result = loginController.login(credentialDTO);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

}
