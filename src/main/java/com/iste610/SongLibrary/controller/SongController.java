package com.iste610.SongLibrary.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.conversions.Bson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.iste610.SongLibrary.model.Comment;
import com.iste610.SongLibrary.model.Song;
import com.iste610.SongLibrary.repository.SongRepository;
import com.mongodb.client.model.Filters;

import static com.mongodb.client.model.Filters.regex;

@Controller
public class SongController
{

    private SongRepository repository;

    public SongController(SongRepository repository)
    {
        this.repository = repository;
    }

    @GetMapping("/songs")
    public String getAllSongs(Model model)
    {
        model.addAttribute("songs", repository.findAll());
        return "index";
    }

    @PostMapping("/songs")
    @PutMapping("/songs")
    public String addOrUpdateSong(Model model, @RequestBody Song song)
    {
        repository.save(song);
        model.addAttribute("songs", repository.findAll());
        return "index";
    }

    @DeleteMapping("/songs/{id}")
    public String deleteSong(Model model, @PathVariable String id)
    {
        repository.deleteById(id);

        model.addAttribute("songs", repository.findAll());
        return "index";
    }

    @GetMapping("/songs/{id}")
    public String getSongById(Model model, @PathVariable String id)
    {
        model.addAttribute("songById", repository.findSongById(id));
        return "index";
    }

    @GetMapping("/songs/{title}")
    public String getSongByTitle(Model model, @PathVariable String title)
    {
        model.addAttribute("songByTitle", repository.findSongsBySong_name(title));
        return "index";
    }

    @GetMapping("/songs/{search}")
    public String getSongByLyrics(Model model, @PathVariable String search)
    {
        List<String> searchWords = Arrays.asList(search.split(" "));
        //Filter koji ce search-at da ima bilo koja rijec iz search string-a u bilo kojem redosljedu(ja mislim :))
        Bson compositeFilter = Filters
            .or(searchWords.stream().map(word -> regex("lyrics", "\\b" + word + "\\b")).collect(Collectors.toList()));

        //TODO: Ne znam kako ovaj regex/filter primjeniti s ovim mongo repository

        return "index";
    }

    @GetMapping("/songs/{artist}")
    public String getSongByArtist(Model model, @PathVariable String artist)
    {
        model.addAttribute("songsByArtist", repository.findSongsByArtist_name(artist));
        return "index";
    }

    //TODO:Mozemo ovo mozda samo preko insert/update Song, pa ne moramo uopce imati ovaj controller nego
    //samo cijeli Song save pa koristimo addOrUpdateSong?
    @PostMapping("/songs/{id}/comments")
    public String addCommentToSong(Model model, @PathVariable String id, @RequestBody Comment comment)
    {
        Song song = repository.findSongById(id);
        song.getComments().add(comment);
        repository.save(song);

        model.addAttribute("songById", repository.findSongById(id));
        return "index";
    }

}

