package com.epam.www.presentation.user;

import com.epam.www.dto.UserDTO;
import com.epam.www.presentation.BaseController;
import com.epam.www.service.UserService;
import com.epam.www.validation.ValidationError;
import com.epam.www.validation.ValidationErrorBuilder;
import com.fasterxml.jackson.databind.deser.Deserializers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create user controller.
 * Map controller to /createuser.
 */
@RestController
@RequestMapping(value = "/api/user")
public class CreateUserController extends BaseController{

    //{"firstName":"Magnolia","lastName":"Rajongo","email":"kevin.smith@gmail.com","password":"1234","account":"555","discount":"normal"}

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateUserController.class);

    @Autowired
    private UserService userService;

    /**
     * createUser. Persist new user data into DB. Using userService.
     * @param userDTO DTO that hold the data for the new user.
     * @return JSON with HTTP status.
     * */
    @RequestMapping(method = RequestMethod.POST,produces = "application/json")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        userService.createUser(userDTO);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }

}
