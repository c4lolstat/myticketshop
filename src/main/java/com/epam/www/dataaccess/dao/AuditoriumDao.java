package com.epam.www.dataaccess.dao;

import com.epam.www.dataaccess.entity.Auditorium;
import com.epam.www.dto.AuditoriumDTO;

/**
 * Created by Farkas on 2017.03.15..
 */
public interface AuditoriumDao {
    void createAuditorium(AuditoriumDTO auditoriumDTO);

    Auditorium readAuditoriumByName(String name);

}
