package com.epam.www.presentation.booking;

import com.epam.www.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Farkas on 2017.03.19..
 */
@RestController
@RequestMapping(value = "/api/booking")
public class CreateBookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity bookTicket(@RequestParam Map<String, String> params, HttpServletRequest request){
        //TODO check params are valid
        bookingService.bookTicket(params,request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
