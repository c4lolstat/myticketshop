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

import java.util.Base64;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

/**
 * Created by Farkas on 2017.02.22..
 */

@RunWith(MockitoJUnitRunner.class)
public class DeleteUserControllerTest {
    //{"firstName":"Magnolia","lastName":"Rajongo","email":"kevin.smith@gmail.com","password":"1234","account":"555","discount":"normal"}

    private static final int GOOD_ID = 2;
    private static final int BAD_ID = 0;

    @Mock
    private UserService userService;

    @InjectMocks
    private DeleteUserController deleteUserController = new DeleteUserController();

    @Test
    public void givenProperInputWhenUserDeletedThenResponseCorrect(){
        ResponseEntity<UserDTO> result = deleteUserController.deleteUser(GOOD_ID);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenProperInputWhenUserDeletedThenServiceMethodCalled(){
        deleteUserController.deleteUser(GOOD_ID);
        Mockito.verify(userService, Mockito.times(1)).deleteUser(anyInt());
    }

    @Test
    public void givenEmptyIdWhenUserDeletedThenResponseIsBadRequest(){
        ResponseEntity<UserDTO> result = deleteUserController.deleteUser(BAD_ID);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

}
