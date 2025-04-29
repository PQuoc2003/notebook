package com.dpquoc.musima.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SongsRequest {

    private String title;

    private MultipartFile song;

    private MultipartFile image;

    private String artist;

    private String album;

}
