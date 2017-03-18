package com.epam.www.service;

import com.epam.www.dto.AuditoriumDTO;

/**
 * Created by Farkas on 2017.03.15..
 */
public interface AuditoriumService {
    void createAuditorium(AuditoriumDTO auditoriumDTO);

    AuditoriumDTO readAuditoriumByName(String name);
}
