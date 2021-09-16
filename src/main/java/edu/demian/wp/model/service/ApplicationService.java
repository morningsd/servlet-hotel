package edu.demian.wp.model.service;

import edu.demian.wp.model.entity.Application;

import java.util.List;

public interface ApplicationService {
    List<Application> findAll();
    List<Application> findAccountApplications(long id);

    void addApplication(Application application);
    void offerApplication(long applicationId, long roomId);

    void delete(long id);
}
