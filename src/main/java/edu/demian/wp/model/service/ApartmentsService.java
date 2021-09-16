package edu.demian.wp.model.service;

import edu.demian.wp.model.entity.Apartments;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ApartmentsService {
    Apartments find(long id);
    List<Apartments> findAll();

    void delete(long id);
    void save(Apartments apartments);
    void update(Apartments apartments);

    String uploadImage(HttpServletRequest request);
    String changeImage(long apartmentsId, HttpServletRequest request);

    String getApartmentsImage(long id);
}
