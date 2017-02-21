package com.epam.www.presentation;

import com.epam.www.dto.UserDTO;
import com.epam.www.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create user controller.
 * Map controller to /createuser.
 */
@RestController
@RequestMapping(value = "/createuser")
public class CreateUserController {

    //{"firstName":"Magnolia","lastName":"Rajongo","email":"kevin.smith@gmail.com","password":"1234","account":"555","discount":"normal"}

    @Autowired
    private IUserService userService;

    /**
     * createUser. Persist new user data into DB. Using userService.
     * @param userDTO DTO that hold the data for the new user.
     * @return JSON with HTTP status.
     * */
    @RequestMapping(method = RequestMethod.POST,produces = "application/json")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (!userDTO.getEmail().isEmpty() && !userDTO.getPassword().isEmpty()) {
            httpStatus = HttpStatus.OK;
            userService.createUser(userDTO);
        }
        return new ResponseEntity<UserDTO>(userDTO, httpStatus);
    }
}
