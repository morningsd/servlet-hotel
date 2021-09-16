package edu.demian.wp.model.dao;

import edu.demian.wp.model.entity.Apartments;

import java.util.List;

public interface ApartmentsDao {
    Apartments find(long id);
    List<Apartments> findAll();

    void delete(long id);
    void save(Apartments room);
    void update(Apartments room);

    void updateStatusId(Apartments room);
}
