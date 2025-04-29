package com.dpquoc.musima.controller;

import com.dpquoc.musima.dto.Playlists.PlaylistsRequest;
import com.dpquoc.musima.entity.Playlists;
import com.dpquoc.musima.entity.Users;
import com.dpquoc.musima.service.PlaylistsService;
import com.dpquoc.musima.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
public class PlaylistsController {


    @Value("${file.upload-directory}")
    private String uploadDirectory;

    private final PlaylistsService playlistsService;

    private final CommonUtils commonUtils;

    @Autowired
    public PlaylistsController(PlaylistsService playlistsService,
                               CommonUtils commonUtils) {
        this.playlistsService = playlistsService;
        this.commonUtils = commonUtils;
    }


    @PostMapping("/playlists/add")
    public ResponseEntity<?> createPlaylist(@ModelAttribute PlaylistsRequest playlistsRequest) {


        try {
            if (!commonUtils.createDirectory())
                return ResponseEntity.badRequest().body("Cannot create directory");

            String imageName = "playlist_quoc" + "_" + UUID.randomUUID() + "_" + playlistsRequest.getImage().getOriginalFilename();

            Path imagePath = Paths.get(uploadDirectory + File.separator + "image" + File.separator + imageName);

            Files.write(imagePath, playlistsRequest.getImage().getBytes());

            String imageUrl = "/uploads/image/" + imageName;

            Playlists playlists = new Playlists(playlistsRequest.getTitle(), imageUrl,new Users());

//            playlistsService.createPlaylist(new Playlists());

            return ResponseEntity.ok(playlists);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

    @GetMapping("/playlists/get")
    public ResponseEntity<?> getPlaylists(@PageableDefault(size = 10, sort = "id") Pageable pageable) {

        playlistsService.getPlaylistsByUserId(1L);

        Page<Playlists> playlists = playlistsService.findAllByUsersId(1L, pageable);

        return ResponseEntity.ok(playlists);


    }


}
