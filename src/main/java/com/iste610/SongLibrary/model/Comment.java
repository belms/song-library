package com.iste610.SongLibrary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment
{
    private String id;
    private String songName;
    private String artistName;
    private String user;
    private String text;

    public Comment(String id) {
        this.id = id;
    }

    public Comment(String id, String songName, String artistName) {
        this.id = id;
        this.songName = songName;
        this.artistName = artistName;
    }
}

