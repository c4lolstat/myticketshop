package com.epam.www.presentation.statistic;

import com.epam.www.dto.BookingDTO;
import com.epam.www.service.impl.HistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Farkas on 2017.04.07..
 */
@RestController
@RequestMapping(value = "/api/history")
public class BookingHistory {

    @Autowired
    private HistoryServiceImpl historyService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<BookingDTO>> getUserBookingHistory(HttpServletRequest request){
        List<BookingDTO> historyList = historyService.getUserBookingHistory(request);
        return new ResponseEntity<List<BookingDTO>>(historyList, HttpStatus.OK);
    }
}
