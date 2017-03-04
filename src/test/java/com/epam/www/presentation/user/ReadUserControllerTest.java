package com.epam.www.presentation.user;

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
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

/**
 * Created by Farkas on 2017.02.22..
 */
@RunWith(MockitoJUnitRunner.class)
public class ReadUserControllerTest {
    //{"firstName":"Magnolia","lastName":"Rajongo","email":"kevin.smith@gmail.com","password":"1234","account":"555","discount":"normal"}

    private static final int GOOD_ID = 2;
    private static final int BAD_ID = 0;

    private CredentialDTO credentialDTO;
    private UserDTO userDTO;

    @Mock
    private UserService userService;

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
        Mockito.when(userService.getUserById(anyInt())).thenReturn(userDTO);
        ResponseEntity<UserDTO> result = readUserController.getUser(GOOD_ID);
        assertEquals(userDTO,result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenProperInputWhenUserRetrievedThenServiceMethodCalled(){
        Mockito.when(userService.getUserById(anyInt())).thenReturn(userDTO);
        readUserController.getUser(GOOD_ID);
        Mockito.verify(userService, Mockito.times(1)).getUserById(anyInt());
    }

    @Test
    public void givenEmptyIdWhenUserRetrievedThenResponseIsBadRequest(){
        ResponseEntity<UserDTO> result = readUserController.getUser(BAD_ID);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

}