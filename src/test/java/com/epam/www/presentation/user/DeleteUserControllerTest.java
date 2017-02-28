package com.epam.www.presentation.user;

import com.epam.www.dto.CredentialDTO;
import com.epam.www.dto.UserDTO;
import com.epam.www.presentation.user.DeleteUserController;
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
import static org.mockito.Matchers.anyString;

/**
 * Created by Farkas on 2017.02.22..
 */

@RunWith(MockitoJUnitRunner.class)
public class DeleteUserControllerTest {
    //{"firstName":"Magnolia","lastName":"Rajongo","email":"kevin.smith@gmail.com","password":"1234","account":"555","discount":"normal"}

    private CredentialDTO credentialDTO;

    @Mock
    private IUserService userService;

    @InjectMocks
    private DeleteUserController deleteUserController = new DeleteUserController();

    @Before
    public void setup(){
        credentialDTO = new CredentialDTO();
        credentialDTO.setEmail("kevin.smith@gmail.com");
        credentialDTO.setPassword("1234");
    }

    @Test
    public void givenProperInputWhenUserDeletedThenResponseCorrect(){
        ResponseEntity<UserDTO> result = deleteUserController.deleteUser(credentialDTO);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenProperInputWhenUserDeletedThenServiceMethodCalled(){
        deleteUserController.deleteUser(credentialDTO);
        Mockito.verify(userService, Mockito.times(1)).deleteUser(anyString());
    }

    @Test
    public void givenEmptyEmailWhenUserDeletedThenResponseIsBadRequest(){
        credentialDTO.setEmail("");
        ResponseEntity<UserDTO> result = deleteUserController.deleteUser(credentialDTO);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void givenEmptyPasswordWhenUserCreatedThenResponseIsBadRequest(){
        credentialDTO.setPassword("");
        ResponseEntity<UserDTO> result = deleteUserController.deleteUser(credentialDTO);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
}
