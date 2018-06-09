package com.kpfu.itis.gasstation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Rustem.
 */
@Service
public class UploadService {
    private final String UPLOAD_DIRECTORY = "upload";
    private final ServletContext servletContext;

    @Autowired
    public UploadService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String upload(MultipartFile[] fileDatas) {
        String uploadPath = servletContext.getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadRootDir = new File(uploadPath);

        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }

        for (MultipartFile fileData : fileDatas) {
            String name = fileData.getOriginalFilename();
            System.out.println("Client File Name = " + name);
            if (name.length() > 0) {
                try {
                    File serverFile = new File(uploadRootDir + File.separator + name);

                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(fileData.getBytes());
                    stream.close();

                    System.out.println("Write file: " + serverFile);
                    return UPLOAD_DIRECTORY + File.separator + serverFile.getName();
                } catch (Exception e) {
                    System.out.println("Error Write file: " + name);
                }
            }
        }
        return "";
    }
}
