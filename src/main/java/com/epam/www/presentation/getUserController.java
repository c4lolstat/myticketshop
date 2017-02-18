package com.epam.www.presentation;

import com.epam.www.dto.UserDTO;
import com.epam.www.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Farkas on 2017.02.18..
 */
@Controller
@RequestMapping(value = "/getuser")
public class getUserController {

    //{"firstName":"Magnolia","lastName":"Rajongo","email":"kevin.smith@gmail.com","password":"1234","account":"555","discount":"normal"}

    @Autowired
    private IUserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public String getUser(@RequestBody UserDTO userDTO){

        UserDTO selected = userService.getUserByEmail(userDTO.getEmail());
        System.out.println(selected);
        return "getuser";
    }
}
