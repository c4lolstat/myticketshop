package com.epam.www.dataaccess.dao.impl;

import com.epam.www.dataaccess.HibernateJPA;
import com.epam.www.dataaccess.dao.*;
import com.epam.www.dataaccess.entity.User;
import com.epam.www.domain.QueryBuilder;
import com.epam.www.dto.UserDTO;
import org.hibernate.annotations.NamedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Farkas on 2017.02.11..
 */
@Repository
@Transactional
public class UserHibernate implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserHibernate.class);
    private final String BASE_QUERY ="FROM User u WHERE";

    private HibernateJPA hibernateJPA;

    public UserHibernate (HibernateJPA hibernateJPA){
        this.hibernateJPA = hibernateJPA;
    }


    @Override
    public void updateUser(UserDTO userDTO) {
        User user = this.getUserByEmail(userDTO.getEmail());
        this.update(user, userDTO);
        this.hibernateJPA.getEntityManager().flush();
    }

    @Override
    public User getUserByEmail(String email) {
        String query = new QueryBuilder()
                .withBaseString(BASE_QUERY)
                .withEmail(email)
                .build();
        List<User> userRecord = this.hibernateJPA.getEntityManager().createQuery(query, User.class).setParameter(1, email).getResultList();
        return userRecord.get(0);
    }

    @Override
    public User getUserById(int id) {
        String query = new QueryBuilder()
                .withBaseString(BASE_QUERY)
                .withId(Integer.valueOf(id).toString())
                .build();
        List<User> userRecord = this.hibernateJPA.getEntityManager().createQuery(query, User.class).setParameter(1, id).getResultList();
       return userRecord.get(0);
    }

    @Override
    public void createUser(UserDTO userDTO) {
        User user = new User();
        this.update(user, userDTO);
        this.hibernateJPA.getEntityManager().persist(user);
    }

    @Override
    public void deleteUser(int id) {
        User user = this.getUserById(id);
        this.hibernateJPA.getEntityManager().remove(user);
    }

    private void update(User user, UserDTO userDTO){
        user.setPassword(userDTO.getPassword());
        user.setLastName(userDTO.getLastName());
        user.setFirstName(userDTO.getFirstName());
        user.setEmail(userDTO.getEmail());
        user.setDiscount(userDTO.getDiscount());
        user.setAccount(userDTO.getAccount());
    }

}
