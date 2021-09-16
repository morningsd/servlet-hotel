package edu.demian.wp.model.service.impl;

import edu.demian.wp.model.dao.ApplicationDao;
import edu.demian.wp.model.dao.ApartmentsDao;
import edu.demian.wp.model.dao.impl.ApplicationDaoImpl;
import edu.demian.wp.model.dao.impl.ApartmentsDaoImpl;
import edu.demian.wp.model.entity.Application;
import edu.demian.wp.model.service.ApplicationService;

import java.util.List;

public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationDao applicationDao = new ApplicationDaoImpl();
    private final ApartmentsDao apartmentsDao = new ApartmentsDaoImpl();

    @Override
    public void addApplication(Application application) {
        applicationDao.save(application);
    }

    @Override
    public List<Application> findAll() {
        return applicationDao.findAll();
    }

    @Override
    public List<Application> findAccountApplications(long id) {
        List<Application> applicationList = applicationDao.findAccountApplications(id);
        setRoomForApplications(applicationList);
        return applicationList;
    }

    private void setRoomForApplications(List<Application> applicationList) {
        for (Application application : applicationList) {
            application.setApartments(apartmentsDao.find(application.getApartmentsId()));
        }
    }

    @Override
    public void offerApplication(long applicationId, long apartmentsId) {
        Application application = applicationDao.find(applicationId);
        application.setApartmentsId(apartmentsId);
        applicationDao.update(application);
    }

    @Override
    public void delete(long id) {
        applicationDao.delete(id);
    }
}
