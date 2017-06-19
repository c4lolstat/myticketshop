package com.epam.www.service;

import com.epam.www.domain.DiscountEnums;
import com.epam.www.dto.UserDTO;

import java.util.List;

/**
 * Created by Farkas on 2017.05.13..
 */
public interface DiscountService {

    List<DiscountEnums> getDiscountForUser(int userId);

}
