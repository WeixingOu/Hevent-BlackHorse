package com.itbh.hevent.services.commons;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileUploadService {
    private static final String UPLOAD_DIRECTORY = "uploads/";

    public String uploadFile(MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();

        if (originalFileName == null || originalFileName.isBlank()) {
            throw new IOException("File name is empty or null.");
        }

        String fileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf("."));

        Path path = Paths.get(UPLOAD_DIRECTORY + fileName);

        Files.createDirectories(path.getParent());
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return "https://example.com/" + UPLOAD_DIRECTORY + fileName;
    }
}
