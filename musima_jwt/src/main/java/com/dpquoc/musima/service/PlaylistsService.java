package com.dpquoc.musima.service;

import com.dpquoc.musima.entity.Playlists;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlaylistsService {

    List<Playlists> getPlaylistsByUserId(Long userId);

    void createPlaylist(Playlists playlist);

    void deletePlaylist(Long playlistId);


    Page<Playlists> findAllByUsersId(Long userId, Pageable pageable);


}
