package com.epam.www.dataaccess.dao;

import com.epam.www.dataaccess.entity.User;
import com.epam.www.dto.UserDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Farkas on 2017.02.11..
 */
public interface UserDao {

    void updateUser(final UserDTO user);

    User getUserById(final int id);

    void createUser(final UserDTO user);

    void deleteUser(final int id);

    User getUserByEmail(String email);
}
