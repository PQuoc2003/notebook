package com.dpquoc.musima.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Playlists implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    public Playlists(String name, String imageUrl, Users users) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.users = users;
    }

    @Override
    public String toString() {
        return "Playlists{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", users=" + users +
                '}';
    }
}
