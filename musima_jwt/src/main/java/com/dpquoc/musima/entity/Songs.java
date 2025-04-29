package com.dpquoc.musima.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Songs implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String songUrl;

    @Column
    private String imageUrl;

    @Column
    private String artist;

    @Column
    private String album;

    public Songs(String name, String songUrl, String imageUrl, String artist, String album) {
        this.name = name;
        this.songUrl = songUrl;
        this.imageUrl = imageUrl;
        this.artist = artist;
        this.album = album;
    }

    @Override
    public String toString() {
        return "Songs{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", songUrl='" + songUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                '}';
    }
}
