package com.epam.www.dataaccess.dao.impl;

import com.epam.www.dataaccess.dao.AuditoriumDao;
import com.epam.www.dataaccess.entity.Auditorium;
import com.epam.www.domain.QueryBuilder;
import com.epam.www.dto.AuditoriumDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Farkas on 2017.03.15..
 */
@Repository
public class AuditoriumHibernate implements AuditoriumDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditoriumHibernate.class);
    private final String BASE_QUERY = "FROM Auditorium a WHERE";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void createAuditorium(AuditoriumDTO auditoriumDTO) {
        Auditorium auditorium = new Auditorium();
        this.update(auditorium,auditoriumDTO);
        entityManager.persist(auditorium);
    }

    @Override
    @Transactional
    public Auditorium readAuditoriumByName(String name) {
        String query = new QueryBuilder().withBaseString(BASE_QUERY).withName(name).build();
        List<Auditorium> auditoriums = entityManager.createQuery(query, Auditorium.class).getResultList();
        return auditoriums.get(0);
    }

    private void update (Auditorium auditorium, AuditoriumDTO auditoriumDTO){
        auditorium.setName(auditoriumDTO.getName());
        auditorium.setVipSeats(auditoriumDTO.getVipSeats());
        auditorium.setNormalSeats(auditoriumDTO.getNormalSeats());
    }
}
