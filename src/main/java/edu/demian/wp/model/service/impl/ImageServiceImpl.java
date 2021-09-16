package edu.demian.wp.model.service.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;
import edu.demian.wp.model.exception.ServiceException;
import edu.demian.wp.model.service.ImageService;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageServiceImpl implements ImageService {
    private final String bucketName = "hotel_bucket-1";
    private final String apiCloudPathAuth = "https://www.googleapis.com/auth/cloud-platform";
    private final String projectId = "hotelproject-325814";
    private final String credentialsFile = "F:\\hotelproject-325814-e22bd5b9097b.json";

    @Override
    public String uploadImageToServer(HttpServletRequest request) throws ServletException, IOException {
        String resultPath;
        Part filePart = request.getPart("file");
        String fileName = getFileName(filePart);
        String temporaryFolder = "/tmp";
        String filePath = request.getServletContext().getRealPath(temporaryFolder);


        try (OutputStream out = new FileOutputStream(filePath + File.separator + fileName);
             InputStream fileContent = filePart.getInputStream()){

            int read;
            final byte[] bytes = new byte[1024];

            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            uploadImageToCloud(fileName, filePath + File.separator + fileName);
            String apiPath = "https://storage.googleapis.com/" + bucketName + "/";
            resultPath = apiPath + fileName;
        } catch (IOException e) {
            throw new ServiceException("Can't save image", e);
        } finally {
            Files.delete(Path.of(filePath + File.separator + fileName));
        }
        return resultPath;
    }

    @Override
    public void uploadImageToCloud(String objectName, String filePath) throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(credentialsFile))
                .createScoped(Lists.newArrayList(apiCloudPathAuth));

        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId(projectId).build().getService();
        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath)));
        System.out.println(
                "File " + filePath + " uploaded to bucket as " + objectName);
    }

    private String getFileName(final Part part) {
        String name = null;
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                name =  content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return System.currentTimeMillis() + "." + FilenameUtils.getExtension(name);
    }

    @Override
    public void deleteImageFromServer(String objectPath) throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(credentialsFile))
                .createScoped(Lists.newArrayList(apiCloudPathAuth));

        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId(projectId).build().getService();

        String objectName = objectPath.substring(objectPath.lastIndexOf('/') + 1);

        storage.delete(bucketName, objectName);
        System.out.println("Object " + objectName + " was deleted from " + bucketName);
    }
}
