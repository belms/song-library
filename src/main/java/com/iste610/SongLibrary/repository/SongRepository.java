package com.iste610.SongLibrary.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.iste610.SongLibrary.model.Comment;
import com.iste610.SongLibrary.model.Song;

public interface SongRepository extends MongoRepository<Song, String> {
    List<Song> findSongsByArtist_name(String artistName);

    Song findSongBySong_name(String songName);
}

