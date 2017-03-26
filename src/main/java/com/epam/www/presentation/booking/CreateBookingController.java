package com.epam.www.presentation.booking;

import com.epam.www.dto.InitBookingDTO;
import com.epam.www.presentation.BaseController;
import com.epam.www.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Farkas on 2017.03.19..
 */
@RestController
@RequestMapping(value = "/api/booking")
public class CreateBookingController extends BaseController{

    @Autowired
    private BookingService bookingService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity bookTicket(@RequestBody InitBookingDTO initBookingDTO, HttpServletRequest request){
        bookingService.bookTicket(initBookingDTO,request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
