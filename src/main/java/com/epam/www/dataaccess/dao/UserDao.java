package com.epam.www.dataaccess.dao;

import com.epam.www.dataaccess.entity.User;
import com.epam.www.dto.UserDTO;

/**
 * Created by Farkas on 2017.02.11..
 */
public interface UserDao {

    void update(UserDTO user);

    User getUserByEmail(final String email);

    void createUser(UserDTO user);

    void deleteUser(final String email);
}
