package com.iste610.SongLibrary.controller;

import com.iste610.SongLibrary.model.Comment;
import com.iste610.SongLibrary.model.Search;
import com.iste610.SongLibrary.model.Song;
import com.iste610.SongLibrary.model.SongForm;
import com.iste610.SongLibrary.repository.SongRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class SongController {
    private final SongRepository repository;

    public SongController(SongRepository repository) {
        this.repository = repository;
    }

    /**
     * Controller redirects user to new song form page
     */
    @RequestMapping(value = "/add-song", method = RequestMethod.GET)
    public String newSong(Model model) {
        model.addAttribute("songForm", new SongForm());
        return "new-song";
    }

    @RequestMapping(value = "/add-song", method = RequestMethod.POST)
    public String newSong(@Valid SongForm songForm, Model model, BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("songForm", songForm);
            return "new-song";
        }
        Song newSong = new Song();
        newSong.setSongName(songForm.getSongName());
        newSong.setArtistName(songForm.getArtistName());
        newSong.setLyrics(songForm.getLyrics());
        // set comment logika
        repository.save(newSong);
        model.addAttribute("search", new Search());
        model.addAttribute("songs", repository.findSongsBySongNameContains(newSong.getSongName()));
        return "index";
    }

    @DeleteMapping("/songs/{id}")
    public String deleteSong(Model model, @PathVariable String id) {
        repository.deleteById(id);

        model.addAttribute("songs", repository.findAll());
        return "index";
    }

    @GetMapping("/songs/{id}")
    public String getSongById(Model model, @PathVariable String id) {
        model.addAttribute("songById", repository.findSongById(id));
        return "index";
    }

    @GetMapping("/songs/{title}")
    public String getSongByTitle(Model model, @PathVariable String title) {
        model.addAttribute("songByTitle", repository.findSongsBySongNameContains(title));
        return "index";
    }

    /**
     * Search
     */
    @RequestMapping(value = "/songs", method = RequestMethod.GET)
    public String searchedSongs(Model model) {
        model.addAttribute("search", new Search());
        return "index";
    }

    /**
     * Controller shows searched songs
     */
    @RequestMapping(value = "/songs", method = RequestMethod.POST)
    public String searchedSongs(@Valid Search search, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("search", search);
            return "index";
        }
        if (!search.getLyrics().isEmpty() || search.getLyrics() != null) {
            System.out.println("trazimo po lyrics: " + search.getLyrics());
            model.addAttribute("songs", repository.findSongByLyricsContains(search.getLyrics()));
        } else if (!search.getArtistName().isEmpty() || search.getArtistName() != null) {
            System.out.println("trazimo po artistname: " + search.getArtistName());
            model.addAttribute("songs", repository.findSongsByArtistNameContains(search.getArtistName()));
        } else if (!search.getSongName().isEmpty() || search.getSongName() != null) {
            model.addAttribute("songs", repository.findSongsBySongNameContains(search.getSongName()));
            System.out.println("trazimo po songname: " + search.getSongName());
        } else model.addAttribute("songs",new ArrayList<>());
        return "index";
    }

    //TODO:Mozemo ovo mozda samo preko insert/update Song, pa ne moramo uopce imati ovaj controller nego
    //samo cijeli Song save pa koristimo addOrUpdateSong?
    @PostMapping("/songs/{id}/comments")
    public String addCommentToSong(Model model, @PathVariable String id, @RequestBody Comment comment) {
        Song song = repository.findSongById(id);
        song.getComments().add(comment);
        repository.save(song);

        model.addAttribute("songById", repository.findSongById(id));
        return "index";
    }

}

