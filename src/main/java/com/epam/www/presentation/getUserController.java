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
 * Get user controller.
 * Map controller to /getuser.
 */
@RestController
@RequestMapping(value = "/getuser")
public class getUserController {

    //{"email":"kevin.smith@gmail.com","password":"1234"}

    @Autowired
    private IUserService userService;

    /**
     * getUser. Select user data from DB. Using userService.
     * @param credentialDTO DTO that hold the user email and password.
     * @return User data in JSON format.
     * */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDTO> getUser(@RequestBody CredentialDTO credentialDTO){
        UserDTO selectedUser = new UserDTO();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (!credentialDTO.getEmail().isEmpty() && !credentialDTO.getPassword().isEmpty()) {
            httpStatus = HttpStatus.OK;
            selectedUser = userService.getUserByEmail(credentialDTO.getEmail());
        }
        return new ResponseEntity<UserDTO>(selectedUser, httpStatus);
    }
}
