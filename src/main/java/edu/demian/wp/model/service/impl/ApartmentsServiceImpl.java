package edu.demian.wp.model.service.impl;

import edu.demian.wp.model.dao.ApartmentsDao;
import edu.demian.wp.model.dao.impl.ApartmentsDaoImpl;
import edu.demian.wp.model.entity.Apartments;
import edu.demian.wp.model.service.ImageService;
import edu.demian.wp.model.service.ApartmentsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

public class ApartmentsServiceImpl implements ApartmentsService {
    private final ApartmentsDao apartmentsDao = new ApartmentsDaoImpl();
    private final ImageService imageService = new ImageServiceImpl();

    @Override
    public Apartments find(long id) {
        return apartmentsDao.find(id);
    }

    @Override
    public List<Apartments> findAll() {
        return apartmentsDao.findAll();
    }

    @Override
    public void delete(long id) {
        Apartments apartments = apartmentsDao.find(id);
        try {
            imageService.deleteImageFromServer(apartments.getImagePath());
        } catch (IOException e) {
            // log or throw maybe
        }
        apartmentsDao.delete(id);
    }

    @Override
    public void save(Apartments apartments) {
        apartmentsDao.save(apartments);
    }

    @Override
    public void update(Apartments apartments) {
        apartmentsDao.update(apartments);
    }

    @Override
    public String uploadImage(HttpServletRequest request) {
        String result = null;
        try {
            result = imageService.uploadImageToServer(request);
        } catch (ServletException | IOException e) {
            // log
        }
        return result;
    }


    @Override
    public String changeImage(long apartmentsId, HttpServletRequest request) {
        String result = null;
        Apartments apartments = apartmentsDao.find(apartmentsId);
        try {
            imageService.deleteImageFromServer(apartments.getImagePath());
            result = imageService.uploadImageToServer(request);
        } catch (IOException | ServletException e) {
            // log or throw
        }
        return result;
    }

    @Override
    public String getApartmentsImage(long id) {
        return apartmentsDao.find(id).getImagePath();
    }

}
