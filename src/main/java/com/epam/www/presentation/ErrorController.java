package com.epam.www.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Farkas on 2017.02.13..
 */
/**
 * ErrorController.
 * <p>
 * Map controller to /error.
 */
@Controller
@RequestMapping("/error")
public class ErrorController {
    /**
     * showErrorWhenGet.
     * @return String Return String for context-mapper.
     * */
    @RequestMapping(method = RequestMethod.GET)
    public String showErrorWhenGet() {
        return "error";
    }

    /**
     * showErrorWhenPost.
     * @return String Return String for context-mapper.
     * */
    @RequestMapping(method = RequestMethod.POST)
    public String showErrorWhenPost() {
        return "error";
    }
}
