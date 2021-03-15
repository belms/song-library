package com.iste610.SongLibrary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongForm {
    private String artistName;
    private String songName;
    private String lyrics;
    private String comment;
    private String user;
}
