package com.epam.www.presentation.booking;

import com.epam.www.dto.BookingInfoDTO;
import com.epam.www.service.BookingService;
import com.epam.www.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Farkas on 2017.03.18..
 */
@RestController
@RequestMapping(value = "/api/bookinginfo")
public class ReadBookingInfoController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<BookingInfoDTO> getBookingInfo (@RequestParam Map<String, String> params){
        BookingInfoDTO result = bookingService.getBookingInformation(params);
        return new ResponseEntity<BookingInfoDTO>(result, HttpStatus.OK);
    }
}
