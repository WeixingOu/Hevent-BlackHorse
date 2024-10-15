package com.itbh.hevent.controllers;

import com.itbh.hevent.exceptions.api.ApiResponse;
import com.itbh.hevent.services.commons.CloudinaryService;
import com.itbh.hevent.services.commons.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class FileUploadController {
    // private final FileUploadService fileUploadService;
    private final CloudinaryService cloudinaryService;
    @Autowired
    public FileUploadController(FileUploadService fileUploadService, CloudinaryService cloudinaryService) {
        //this.fileUploadService = fileUploadService;
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<Map<String, String>>> uploadCoverImg(@RequestParam MultipartFile file) throws IOException {
        String fileUrl = cloudinaryService.uploadFile(file);

        Map<String, String> response = new HashMap<>();
        response.put("coverImgUrl", fileUrl);

        return new ResponseEntity<>(ApiResponse.success(response), HttpStatus.OK);
    }
}
