package com.epam.www.presentation;

import com.epam.www.dto.CredentialDTO;
import com.epam.www.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Farkas on 2017.03.03..
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    /**
     * login. Authenticate user with userService.
     * @param credentialDTO DTO that hold the data for the new user.
     * @return HTTP status with autentication stuff.
     * */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody CredentialDTO credentialDTO){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        HttpHeaders headers = new HttpHeaders();
        if (!credentialDTO.getEmail().isEmpty() && !credentialDTO.getPassword().isEmpty()){
            httpStatus = HttpStatus.OK;
            String autToken = userService.authenticateUser(credentialDTO);
            headers.set("Authorization", autToken);
        }
        return new ResponseEntity<String>("login attempt",headers, httpStatus);
    }
}
