package com.epam.www.service.impl;

import com.epam.www.auth.JwtUtil;
import com.epam.www.dataaccess.dao.UserDao;
import com.epam.www.dataaccess.entity.User;
import com.epam.www.dto.CredentialDTO;
import com.epam.www.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

/**
 * Created by Farkas on 2017.02.22..
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private User user;
    private CredentialDTO credentialDTO;

    @Mock
    private UserDao userDao;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl();

    @Before
    public void setup(){
        user = new User();
        user.setFirstName("Magnolia");
        user.setLastName("Rajongo");
        user.setEmail("kevin.smith@gmail.com");
        user.setPassword("1234");
        user.setAccount(BigDecimal.ZERO);
        user.setDiscount("normal");

        credentialDTO = new CredentialDTO();
        credentialDTO.setEmail("kevin.smith@gmail.com");
        credentialDTO.setPassword("1234");
    }

    @Test
    public void whenUserCreatedThenDaoMethodCalled(){
        userService.createUser(any(UserDTO.class));
        Mockito.verify(userDao, Mockito.times(1)).createUser(any(UserDTO.class));
    }

    @Test
    public void whenUserDeletedThenDaoMethodCalled(){
        userService.deleteUser(anyInt());
        Mockito.verify(userDao, Mockito.times(1)).deleteUser(anyInt());
    }

    @Test
    public void whenUserUpdatedCreatedThenDaoMethodCalled(){
        userService.updateUser(any(UserDTO.class));
        Mockito.verify(userDao, Mockito.times(1)).updateUser(any(UserDTO.class));
    }

    @Test
    public void whenUserReadThenUserDTOReturned(){
        Mockito.when(userDao.getUserById(anyInt())).thenReturn(user);
        UserDTO result = userService.getUserById(anyInt());
        assertEquals("Magnolia",result.getFirstName());
        assertEquals("Rajongo",result.getLastName());
        assertEquals("1234",result.getPassword());
        assertEquals("kevin.smith@gmail.com",result.getEmail());
        assertEquals(BigDecimal.ZERO,result.getAccount());
        assertEquals("normal",result.getDiscount());
    }

    @Test
    public void givenValidCredentialWhenAuthenticateUserThenJwtTokenReturned(){
        Mockito.when(userDao.getUserByEmail(anyString())).thenReturn(user);
        Mockito.when(jwtUtil.generateToken(any(UserDTO.class))).thenReturn("testToken");
        String result = userService.authenticateUser(credentialDTO);
        assertEquals("testToken",result);
    }

    @Test
    public void givenUserNotFoundWhenAuthenticateUserThenEmptyTokenReturned(){
        Mockito.when(userDao.getUserByEmail(anyString())).thenReturn(null);
        String result = userService.authenticateUser(credentialDTO);
        assertEquals("",result);
    }

    @Test
    public void givenCredentialWithWrongPSWWhenAuthenticateUserThenEmptyTokenReturned(){
        credentialDTO.setPassword("4321");
        Mockito.when(userDao.getUserByEmail(anyString())).thenReturn(user);
        String result = userService.authenticateUser(credentialDTO);
        assertEquals("",result);
    }
}
