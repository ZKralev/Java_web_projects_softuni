package com.example.musicdb.model.entity;

import com.example.musicdb.model.enums.ArtistEnum;

import javax.persistence.*;

@Entity
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ArtistEnum name;

    @Column(name = "career_information", columnDefinition = "TEXT")
    private String carrierInfo;

    public Artist(ArtistEnum type) {
        this.name = type;
    }

    public Artist() {

    }


    public ArtistEnum getName() {
        return name;
    }

    public void setName(ArtistEnum name) {
        this.name = name;
    }

    public String getCarrierInfo() {
        return carrierInfo;
    }

    public void setCarrierInfo(String carrierInfo) {
        this.carrierInfo = carrierInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
