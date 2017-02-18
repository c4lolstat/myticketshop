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
@RequestMapping(value = "/updateuser")
public class UpdateUser {

    //{"firstName":"Magnolia","lastName":"Rajongo","email":"kevin.smith@gmail.com","password":"4321","account":"333","discount":"bday"}

    @Autowired
    private IUserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public String updateUser(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.toString());
        userService.updateUser(userDTO);
        return "updateuser";
    }
}
