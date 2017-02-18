package com.epam.www.service.impl;

import com.epam.www.dataaccess.dao.UserDao;
import com.epam.www.dataaccess.entity.User;
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

    @Override
    public void deleteUser(UserDTO userDTO) {
        userDao.deleteUser(userDTO.getEmail());
    }

    @Override
    public UserDTO getUserByEmail(String email) {
       User user = userDao.getUserByEmail(email);
        return new UserDTO(user);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        userDao.update(userDTO);
    }


}
