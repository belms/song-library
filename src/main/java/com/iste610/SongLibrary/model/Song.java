package com.iste610.SongLibrary.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "songs")
public class Song
{
    @Id
    private String id;
    private String artist_name;
    private String artist_url;
    private String song_name;
    private String lyrics;
    private List<Comment> comments;
}

