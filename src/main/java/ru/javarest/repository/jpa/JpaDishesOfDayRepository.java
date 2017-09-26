package ru.javarest.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javarest.model.DishesOfDay;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaDishesOfDayRepository {

    @PersistenceContext
    EntityManager em;

//    public List<DishesOfDay> getAll()


}
