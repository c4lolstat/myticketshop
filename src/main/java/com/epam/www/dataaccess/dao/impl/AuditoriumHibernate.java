package com.epam.www.dataaccess.dao.impl;

import com.epam.www.dataaccess.HibernateJPA;
import com.epam.www.dataaccess.dao.AuditoriumDao;
import com.epam.www.dataaccess.entity.Auditorium;
import com.epam.www.dto.AuditoriumDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Farkas on 2017.03.15..
 */
@Repository
@Transactional
public class AuditoriumHibernate implements AuditoriumDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditoriumHibernate.class);

    private HibernateJPA hibernateJPA;

    public AuditoriumHibernate (HibernateJPA hibernateJPA){
        this.hibernateJPA = hibernateJPA;
    }

    @Override
    public void createAuditorium(AuditoriumDTO auditoriumDTO) {
        Auditorium auditorium = new Auditorium();
        this.update(auditorium,auditoriumDTO);
        this.hibernateJPA.getEntityManager().persist(auditorium);
    }

    @Override
    public Auditorium readAuditoriumByName(String name) {
        String query = "FROM Auditorium a WHERE a.name=?1";
        List<Auditorium> auditoriums = this.hibernateJPA.getEntityManager().createQuery(query, Auditorium.class).setParameter(1, name).getResultList();
        return auditoriums.get(0);
    }

    private void update (Auditorium auditorium, AuditoriumDTO auditoriumDTO){
        auditorium.setName(auditoriumDTO.getName());
        auditorium.setVipSeats(auditoriumDTO.getVipSeats());
        auditorium.setNormalSeats(auditoriumDTO.getNormalSeats());
    }
}
