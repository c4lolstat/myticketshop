package com.epam.www;

/**
 * Created by Farkas on 2017.02.06..
 */

import com.epam.www.dataaccess.dao.impl.UserHibernate;
import com.epam.www.dto.UserDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {

    public static void main(String args[]){
        ApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");

        System.out.print("Hello Spring");

        UserDTO user = new UserDTO.Builder()
                .setFirstName("Magnolia")
                .setLastName("Rajongo")
                .setEmail("someemail@gmail.com")
                .setPassword("sobadpassword")
                .setAccount(5000)
                .build();

        System.out.println(user);

        UserHibernate userHibernate = (UserHibernate) context.getBean("userHibernate");

        System.out.println(userHibernate);

        userHibernate.createUser(user);
    }
}
