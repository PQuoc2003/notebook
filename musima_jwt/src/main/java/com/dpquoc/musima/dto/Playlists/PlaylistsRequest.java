package com.dpquoc.musima.dto.Playlists;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistsRequest {

    private String title;
    private MultipartFile image;

}
