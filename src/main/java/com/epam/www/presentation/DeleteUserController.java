package com.epam.www.presentation;

import com.epam.www.dto.CredentialDTO;
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
@RequestMapping(value = "/deleteuser")
public class DeleteUserController {

    @Autowired
    private IUserService userService;

    /**
     * deleteUser. update existing user data in DB. Using userService.
     * @param credentialDTO DTO that hold the user email and password.
     * @return JSON with HTTP status.
     * */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<UserDTO> deleteUser(@RequestBody CredentialDTO credentialDTO){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (!credentialDTO.getEmail().isEmpty() && !credentialDTO.getPassword().isEmpty()) {
            httpStatus = HttpStatus.OK;
            userService.deleteUser(credentialDTO.getEmail());
        }
        return new ResponseEntity<UserDTO>(httpStatus);
    }
}
