package com.epam.www.dataaccess.dao.impl;

import com.epam.www.dataaccess.HibernateJPA;
import com.epam.www.dataaccess.dao.*;
import com.epam.www.dto.UserDTO;

/**
 * Created by Farkas on 2017.02.11..
 */
public class UserHibernate implements UserDao {

    HibernateJPA hibernateJPA;

    public UserHibernate (HibernateJPA hibernateJPA){
        this.hibernateJPA = hibernateJPA;
    }


    @Override
    public void update(UserDTO user) {

    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return null;
    }

    @Override
    public void createUser(UserDTO user) {

    }

    @Override
    public void deleteUser(String email) {

    }

    @Override
    public String toString() {
        return "UserHibernate{" +
                "hibernateJPA=" + hibernateJPA +
                '}';
    }
}
