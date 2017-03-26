package com.epam.www.presentation.booking;

import com.epam.www.dto.BookingInfoDTO;
import com.epam.www.service.BookingService;
import com.epam.www.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Farkas on 2017.03.18..
 */
@RestController
@RequestMapping(value = "/api/bookinginfo/{id}")
public class ReadBookingInfoController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<BookingInfoDTO> getBookingInfo (@PathVariable("id")int id){
        BookingInfoDTO result = bookingService.getBookingInformation(id);
        return new ResponseEntity<BookingInfoDTO>(result, HttpStatus.OK);
    }
}
