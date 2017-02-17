package com.epam.www.service.impl;

import com.epam.www.dataaccess.dao.UserDao;
import com.epam.www.dto.UserDTO;
import com.epam.www.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Farkas on 2017.02.17..
 */

@Component
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void createUser(UserDTO userDTO) {
        userDao.createUser(userDTO);
    }
}
