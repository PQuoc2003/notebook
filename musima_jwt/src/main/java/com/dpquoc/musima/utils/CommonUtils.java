package com.dpquoc.musima.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class CommonUtils {

    @Value("${file.upload-directory}")
    private String uploadDirectory;

    public boolean createDirectory() {


        File directory = new File(uploadDirectory);

        String songDirectory = uploadDirectory + File.separator + "song";
        String imageDirectory = uploadDirectory + File.separator + "image";

        File songDirectoryFile = new File(songDirectory);
        File imageDirectoryFile = new File(imageDirectory);

        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                return false;
            }

        }

        if (!songDirectoryFile.exists()) {
            if (!songDirectoryFile.mkdirs()) {
                return false;
            }
        }

        if (!imageDirectoryFile.exists()) {
            if (!imageDirectoryFile.mkdirs()) {
                return false;
            }
        }

        return true;


    }

}
