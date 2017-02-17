package com.epam.www.presentation;

import com.epam.www.dto.UserDTO;
import com.epam.www.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Farkas on 2017.02.17..**/
@Controller
@RequestMapping(value = "/createuser")
public class AddUserController {

    //{"firstName":"Magnolia","lastName":"Rajongo","email":"kevin.smith@gmail.com","password":"1234","account":"555","discount":"normal"}

    @Autowired
    private IUserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public String createUser(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.toString());
        userService.createUser(userDTO);
        return "createuser";
    }
}
