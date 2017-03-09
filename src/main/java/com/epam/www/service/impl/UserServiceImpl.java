package com.epam.www.service.impl;

import com.epam.www.auth.JwtUtil;
import com.epam.www.dataaccess.dao.UserDao;
import com.epam.www.dataaccess.entity.User;
import com.epam.www.dto.CredentialDTO;
import com.epam.www.dto.UserDTO;
import com.epam.www.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Farkas on 2017.02.17..
 */

@Component
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void createUser(UserDTO userDTO) {
        userDao.createUser(userDTO);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public UserDTO getUserById(int id) {
        User user = userDao.getUserById(id);
        return new UserDTO(user);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        userDao.updateUser(userDTO);
    }

    @Override
    public String authenticateUser(CredentialDTO credentialDTO) {
        String response = "";
        User user = userDao.getUserByEmail(credentialDTO.getEmail());
        if (user != null && credentialDTO.getPassword().equals(user.getPassword())){
            UserDTO userDTO = new UserDTO(user);
            response = jwtUtil.generateToken(userDTO);
        }

        return response;
    }


}
