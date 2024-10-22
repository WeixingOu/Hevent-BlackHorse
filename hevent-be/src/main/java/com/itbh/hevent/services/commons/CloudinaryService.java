package com.itbh.hevent.services.commons;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadFile(MultipartFile file, String publicId) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        Map params = ObjectUtils.asMap(
            "use_filename", true,
            "unique_filename", false,
            "overwrite", true,
            "public_id", publicId
        );

        return cloudinary.uploader().upload(file.getBytes(), params).get("url").toString();
    }

}
