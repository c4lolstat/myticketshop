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
@RequestMapping(value = "/deleteuser")
public class DeleteUserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteUser(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.toString());
        userService.deleteUser(userDTO);
        return "createuser";
    }
}
