package com.iste610.SongLibrary.repository;

import com.iste610.SongLibrary.model.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SongRepository extends MongoRepository<Song, String>{

    List<Song> findSongsByArtistNameContains(String artistName);

    List<Song> findSongsBySongNameContains(String songName);

    Song findSongById(String id);

    List<Song> findSongByLyricsContains(String partOfLyrics);
}

