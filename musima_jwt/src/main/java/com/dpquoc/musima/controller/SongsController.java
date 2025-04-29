package com.dpquoc.musima.controller;

import com.dpquoc.musima.dto.SongsRequest;
import com.dpquoc.musima.entity.Songs;
import com.dpquoc.musima.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
public class SongsController {


    @Value("${file.upload-directory}")
    private String uploadDirectory;

    private final CommonUtils commonUtils;

    @Autowired
    public SongsController(CommonUtils commonUtils) {
        this.commonUtils = commonUtils;
    }

    @PostMapping("/songs/upload")
    public ResponseEntity<?> uploadSong(@ModelAttribute SongsRequest songsRequest) {

        try {

            if(!commonUtils.createDirectory())
                return ResponseEntity.badRequest().body("Cannot create directory");


            String songName = "song_quoc" + "_" + UUID.randomUUID() + "_" + songsRequest.getSong().getOriginalFilename();

            String imageName =  "image_quoc" + "_" + UUID.randomUUID() + "_" + songsRequest.getImage().getOriginalFilename();

            Path songPath = Paths.get(uploadDirectory + File.separator + "song" + File.separator + songName);

            Path imagePath = Paths.get(uploadDirectory + File.separator + "image" + File.separator + imageName);

            Files.write(songPath, songsRequest.getSong().getBytes());

            Files.write(imagePath, songsRequest.getImage().getBytes());

            String songUrl = "/uploads/song/" + songName;

            String imageUrl = "/uploads/image/" + imageName;

            Songs songs = new Songs(
                    songsRequest.getTitle(),
                    songUrl,
                    imageUrl,
                    songsRequest.getArtist(),
                    songsRequest.getAlbum()
            );


            return ResponseEntity.ok(songs);

        }
        catch (IOException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


}
