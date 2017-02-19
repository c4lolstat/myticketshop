package com.epam.www.service;

import com.epam.www.dto.UserDTO;

/**
 * Created by Farkas on 2017.02.17..
 */
public interface IUserService {

    void createUser(UserDTO userDTO);

    void deleteUser(String email);

    UserDTO getUserByEmail(String email);

    void updateUser(UserDTO userDTO);
}
