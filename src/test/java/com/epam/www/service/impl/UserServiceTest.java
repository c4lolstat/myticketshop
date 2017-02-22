package com.epam.www.service.impl;

import com.epam.www.dataaccess.dao.UserDao;
import com.epam.www.dataaccess.entity.User;
import com.epam.www.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

/**
 * Created by Farkas on 2017.02.22..
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private User user;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService = new UserService();

    @Before
    public void setup(){
        user = new User();
        user.setFirstName("Magnolia");
        user.setLastName("Rajongo");
        user.setEmail("kevin.smith@gmail.com");
        user.setPassword("1234");
        user.setAccount(555);
        user.setDiscount("normal");
    }

    @Test
    public void whenUserCreatedThenDaoMethodCalled(){
        userService.createUser(any(UserDTO.class));
        Mockito.verify(userDao, Mockito.times(1)).createUser(any(UserDTO.class));
    }

    @Test
    public void whenUserDeletedThenDaoMethodCalled(){
        userService.deleteUser(anyString());
        Mockito.verify(userDao, Mockito.times(1)).deleteUser(anyString());
    }

    @Test
    public void whenUserUpdatedCreatedThenDaoMethodCalled(){
        userService.updateUser(any(UserDTO.class));
        Mockito.verify(userDao, Mockito.times(1)).updateUser(any(UserDTO.class));
    }

    @Test
    public void whenUserReadThenUserDTOReturned(){
        Mockito.when(userDao.getUserByEmail(anyString())).thenReturn(user);
        UserDTO result = userService.getUserByEmail(anyString());
        assertEquals("Magnolia",result.getFirstName());
        assertEquals("Rajongo",result.getLastName());
        assertEquals("1234",result.getPassword());
        assertEquals("kevin.smith@gmail.com",result.getEmail());
        assertEquals(555,result.getAccount());
        assertEquals("normal",result.getDiscount());
    }
}
