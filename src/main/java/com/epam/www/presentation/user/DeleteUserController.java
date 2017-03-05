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
 * Update user controller.
 * Map controller to /updateuser.
 */
@RestController
@RequestMapping(value = "/user/{id}")
public class DeleteUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteUserController.class);

    @Autowired
    private UserService userService;

    /**
     * deleteUser. update existing user data in DB. Using userService.
     * @param id of the user to delete.
     * @return JSON with HTTP status.
     * */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<UserDTO> deleteUser(@PathVariable ("id") int id){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (id > 0) {
            httpStatus = HttpStatus.OK;
            userService.deleteUser(id);
        }
        return new ResponseEntity<UserDTO>(httpStatus);
    }
}
