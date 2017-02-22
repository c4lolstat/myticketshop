package com.epam.www.presentation;

import com.epam.www.dto.CredentialDTO;
import com.epam.www.dto.UserDTO;
import com.epam.www.service.IUserService;
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
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.anyString;

/**
 * Created by Farkas on 2017.02.22..
 */
@RunWith(MockitoJUnitRunner.class)
public class ReadUserControllerTest {
    //{"firstName":"Magnolia","lastName":"Rajongo","email":"kevin.smith@gmail.com","password":"1234","account":"555","discount":"normal"}

    private CredentialDTO credentialDTO;
    private UserDTO userDTO;

    @Mock
    private IUserService userService;

    @InjectMocks
    private ReadUserController readUserController = new ReadUserController();

    @Before
    public void setup(){
        userDTO = new UserDTO();
        userDTO.setFirstName("Magnolia");
        userDTO.setLastName("Rajongo");
        userDTO.setEmail("kevin.smith@gmail.com");
        userDTO.setPassword("1234");
        userDTO.setAccount(555);
        userDTO.setDiscount("normal");

        credentialDTO = new CredentialDTO();
        credentialDTO.setEmail("kevin.smith@gmail.com");
        credentialDTO.setPassword("1234");
    }

    @Test
    public void givenProperInputWhenUserRetrievedThenResponseCorrect(){
        Mockito.when(userService.getUserByEmail(anyString())).thenReturn(userDTO);
        ResponseEntity<UserDTO> result = readUserController.getUser(credentialDTO);
        assertEquals(userDTO,result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenProperInputWhenUserRetrievedThenServiceMethodCalled(){
        Mockito.when(userService.getUserByEmail(anyString())).thenReturn(userDTO);
        readUserController.getUser(credentialDTO);
        Mockito.verify(userService, Mockito.times(1)).getUserByEmail(anyString());
    }

    @Test
    public void givenEmptyEmailWhenUserRetrievedThenResponseIsBadRequest(){
        credentialDTO.setEmail("");
        ResponseEntity<UserDTO> result = readUserController.getUser(credentialDTO);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void givenEmptyPasswordWhenUserRetrievedThenResponseIsBadRequest(){
        credentialDTO.setPassword("");
        ResponseEntity<UserDTO> result = readUserController.getUser(credentialDTO);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void givenWrongPasswordWhenUserRetrievedThenResponseIsUnauthorized(){
        credentialDTO.setPassword("4321");
        Mockito.when(userService.getUserByEmail(anyString())).thenReturn(userDTO);
        ResponseEntity<UserDTO> result = readUserController.getUser(credentialDTO);
        assertNotEquals(userDTO,result.getBody());
        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }
}