package edu.demian.wp.model.dao;

import edu.demian.wp.model.entity.Application;

import java.util.List;

public interface ApplicationDao {
    Application find(long id);
    List<Application> findAll();
    List<Application> findAccountApplications(long id);

    void save(Application application);

    void update(Application application);

    void delete(long id);
}
