package com.epam.www.dataaccess.dao.impl;

import com.epam.www.dataaccess.dao.UserDao;
import com.epam.www.dataaccess.entity.User;
import com.epam.www.domain.QueryBuilder;
import com.epam.www.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Farkas on 2017.02.11..
 */
@Repository
public class UserHibernate implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserHibernate.class);
    private final String BASE_QUERY ="FROM User u WHERE";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void updateUser(UserDTO userDTO) {
        User user = this.getUserByEmail(userDTO.getEmail());
        this.update(user, userDTO);
        entityManager.flush();
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        String query = new QueryBuilder()
                .withBaseString(BASE_QUERY)
                .withEmail(email)
                .build();
        List<User> userRecord = entityManager.createQuery(query, User.class).getResultList();
        return userRecord.get(0);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        String query = new QueryBuilder()
                .withBaseString(BASE_QUERY)
                .withId(Integer.valueOf(id).toString())
                .build();
        List<User> userRecord = entityManager.createQuery(query, User.class).getResultList();
       return userRecord.get(0);
    }

    @Override
    @Transactional
    public void createUser(UserDTO userDTO) {
        User user = new User();
        this.update(user, userDTO);
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        User user = this.getUserById(id);
        entityManager.remove(user);
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
