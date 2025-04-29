package com.dpquoc.musima.service.impl;

import com.dpquoc.musima.entity.Playlists;
import com.dpquoc.musima.repository.PlaylistsRepository;
import com.dpquoc.musima.service.PlaylistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistsServiceImpl implements PlaylistsService {

    private final PlaylistsRepository playlistsRepository;

    @Autowired
    public PlaylistsServiceImpl(PlaylistsRepository playlistsRepository) {
        this.playlistsRepository = playlistsRepository;
    }

    @Override
    public List<Playlists> getPlaylistsByUserId(Long userId) {
        return playlistsRepository.findByUsersId(userId);
    }

    @Override
    public void createPlaylist(Playlists playlist) {
        playlistsRepository.save(playlist);

    }

    @Override
    public void deletePlaylist(Long playlistId) {
        playlistsRepository.deleteById(playlistId);
    }

    @Override
    public Page<Playlists> findAllByUsersId(Long userId, Pageable pageable) {
        return playlistsRepository.findAllByUsersId(userId, pageable);
    }
}
