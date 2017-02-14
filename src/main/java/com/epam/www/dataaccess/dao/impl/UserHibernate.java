package com.epam.www.dataaccess.dao.impl;

import com.epam.www.dataaccess.HibernateJPA;
import com.epam.www.dataaccess.dao.*;
import com.epam.www.dataaccess.entity.User;
import com.epam.www.dto.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Farkas on 2017.02.11..
 */
@Repository
public class UserHibernate implements UserDao {

    HibernateJPA hibernateJPA;

    public UserHibernate (HibernateJPA hibernateJPA){
        this.hibernateJPA = hibernateJPA;
    }


    @Override
    public void update(UserDTO userDTO) {
        User user = new User();
        this.update(user, userDTO);
        this.hibernateJPA.getEntityManager().refresh(user);
    }

    @Override
    public User getUserByEmail(String email) {
//        String query = "FROM User r WHERE r.email=?1";
//        List<User> userRecord = this.hibernateJPA.getEntityManager().createQuery(query, User.class).setParameter(1, email).getResultList();
//        return userRecord.get(0);
        return new User();
    }

    @Override
    public void createUser(UserDTO userDTO) {

        User user = new User();
        this.update(user, userDTO);
        this.hibernateJPA.getEntityManager().persist(user);

    }

    @Override
    public void deleteUser(String email) {

    }

    private void update(User user, UserDTO userDTO){
        user.setPassword(userDTO.getPassword());
        user.setLastName(userDTO.getLastName());
        user.setFirstName(userDTO.getFirstName());
        user.setEmail(userDTO.getEmail());
        user.setDiscount(userDTO.getDiscount());
        user.setAccount(userDTO.getAccount());
    }

    @Override
    public String toString() {
        return "UserHibernate{" +
                "hibernateJPA=" + hibernateJPA +
                '}';
    }
}
