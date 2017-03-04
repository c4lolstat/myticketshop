package com.epam.www.presentation.user;

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
import static org.mockito.Matchers.any;

/**
 * Created by Farkas on 2017.02.22..
 */

@RunWith(MockitoJUnitRunner.class)
public class UpdateUserControllerTest {
    //{"firstName":"Magnolia","lastName":"Rajongo","email":"kevin.smith@gmail.com","password":"1234","account":"555","discount":"normal"}


    private UserDTO userDTO;

    @Mock
    private UserService userService;

    @InjectMocks
    private UpdateUserController updateUserController = new UpdateUserController();

    @Before
    public void setup(){
        userDTO = new UserDTO();
        userDTO.setFirstName("Magnolia");
        userDTO.setLastName("Rajongo");
        userDTO.setEmail("kevin.smith@gmail.com");
        userDTO.setPassword("1234");
        userDTO.setAccount(555);
        userDTO.setDiscount("normal");
    }

    @Test
    public void givenProperInputWhenUserUpdatedThenResponseCorrect(){
        ResponseEntity<UserDTO> result = updateUserController.updateUser(userDTO);
        assertEquals(userDTO, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenProperInputWhenUserUpdatedThenServiceMethodCalled(){
        updateUserController.updateUser(userDTO);
        Mockito.verify(userService, Mockito.times(1)).updateUser(any(UserDTO.class));
    }

    @Test
    public void givenEmptyEmailWhenUserUpdatedThenResponseIsBadRequest(){
        userDTO.setEmail("");
        ResponseEntity<UserDTO> result = updateUserController.updateUser(userDTO);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void givenEmptyPasswordWhenUserUpdatedThenResponseIsBadRequest(){
        userDTO.setPassword("");
        ResponseEntity<UserDTO> result = updateUserController.updateUser(userDTO);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
}
