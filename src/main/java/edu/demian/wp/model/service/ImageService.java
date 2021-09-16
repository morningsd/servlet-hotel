package edu.demian.wp.model.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface ImageService {
    String uploadImageToServer(HttpServletRequest request) throws ServletException, IOException;
    void uploadImageToCloud(String objectName, String filePath) throws IOException;
    void deleteImageFromServer(String objectPath) throws IOException;
}
