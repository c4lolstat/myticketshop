package com.epam.www.presentation.user;

import com.epam.www.dto.UserDTO;
import com.epam.www.presentation.BaseController;
import com.epam.www.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Update user controller.
 * Map controller to /updateuser.
 */

@RestController
@RequestMapping(value = "api//user")
public class UpdateUserController extends BaseController {

    //{"firstName":"Magnolia","lastName":"Rajongo","email":"kevin.smith@gmail.com","password":"4321","account":"333","discount":"bday"}

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateUserController.class);

    @Autowired
    private UserService userService;

    /**
     * updateUser. update existing user data in DB. Using userService.
     * @param userDTO DTO that hold the update data for the user.
     * @return JSON with HTTP status.
     * */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (!userDTO.getEmail().isEmpty() && !userDTO.getPassword().isEmpty()) {
            httpStatus = HttpStatus.OK;
            userService.updateUser(userDTO);
        }
        return new ResponseEntity<UserDTO>(userDTO, httpStatus);
    }
}
