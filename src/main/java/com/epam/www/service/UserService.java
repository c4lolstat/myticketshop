package com.epam.www.service;

import com.epam.www.dto.CredentialDTO;
import com.epam.www.dto.UserDTO;

/**
 * Created by Farkas on 2017.02.17..
 */
public interface UserService {

    void createUser(final UserDTO userDTO);

    void deleteUser(final int id);

    UserDTO getUserById(final int id);

    void updateUser(final UserDTO userDTO);

    String authenticateUser(final CredentialDTO credentialDTO);
}
