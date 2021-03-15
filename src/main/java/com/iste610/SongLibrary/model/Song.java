package com.iste610.SongLibrary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "songs")
public class Song
{
    @Id
    private String id;
    @Field("ARTIST_NAME")
    private String artistName;
    @Field("SONG_NAME")
    private String songName;
    @Field("LYRICS")
    private String lyrics;
    @Field("COMMENTS")
    private List<Comment> comments;
}

