package com.epam.www.service.impl;

import com.epam.www.dataaccess.dao.AuditoriumDao;
import com.epam.www.dataaccess.entity.Auditorium;
import com.epam.www.dto.AuditoriumDTO;
import com.epam.www.service.AuditoriumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Farkas on 2017.03.15..
 */
@Component
public class AuditoriumServiceImpl implements AuditoriumService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuditoriumServiceImpl.class);

    @Autowired
    private AuditoriumDao auditoriumDao;

    @Override
    public void createAuditorium(AuditoriumDTO auditoriumDTO) {
        auditoriumDao.createAuditorium(auditoriumDTO);
    }

    @Override
    public AuditoriumDTO readAuditoriumByName(String name) {

        if (name != null && !name.isEmpty()) {
            Auditorium auditorium = auditoriumDao.readAuditoriumByName(name);
            return new AuditoriumDTO(auditorium);
        }
        return new AuditoriumDTO();
    }
}
