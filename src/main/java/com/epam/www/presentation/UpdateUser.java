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
 * Update user controller.
 * Map controller to /updateuser.
 */

@RestController
@RequestMapping(value = "/updateuser")
public class UpdateUser {

    //{"firstName":"Magnolia","lastName":"Rajongo","email":"kevin.smith@gmail.com","password":"4321","account":"333","discount":"bday"}

    @Autowired
    private IUserService userService;

    /**
     * updateUser. update existing user data in DB. Using userService.
     * @param userDTO DTO that hold the update data for the user.
     * @return JSON with HTTP status.
     * */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (!userDTO.getEmail().isEmpty() && !userDTO.getPassword().isEmpty()) {
            httpStatus = HttpStatus.OK;
            userService.updateUser(userDTO);
        }
        return new ResponseEntity<UserDTO>(userDTO, httpStatus);
    }
}
