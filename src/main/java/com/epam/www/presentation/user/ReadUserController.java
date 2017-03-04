package com.epam.www.presentation.user;

import com.epam.www.dto.CredentialDTO;
import com.epam.www.dto.UserDTO;
import com.epam.www.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Get user controller.
 * Map controller to /getuser.
 */
@RestController
@RequestMapping(value = "/user/{id}")
public class ReadUserController {

    //{"email":"kevin.smith@gmail.com","password":"1234"}

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadUserController.class);

    @Autowired
    private UserService userService;

    /**
     * getUser. Select user data from DB. Using userService.
     * @param id of the usert to get.
     * @return User data in JSON format.
     * */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUser(@PathVariable(name = "id",required = true)int id){
        UserDTO selectedUser = new UserDTO();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (id > 0) {
            selectedUser = userService.getUserById(id);
            httpStatus = HttpStatus.OK;
        }
        return new ResponseEntity<UserDTO>(selectedUser, httpStatus);
    }
}
