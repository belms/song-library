package com.iste610.SongLibrary.model;

public class Search {
    private String lyrics, artistName, songName;

    public Search() {
    }

    public Search(String lyrics, String artistName, String songName) {
        this.lyrics = lyrics;
        this.artistName = artistName;
        this.songName = songName;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
}
