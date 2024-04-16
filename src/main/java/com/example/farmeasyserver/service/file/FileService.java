package com.example.farmeasyserver.service.file;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public void upload(MultipartFile file, String fileName) throws FileUploadException;
    public void delete(String fileName);
}
