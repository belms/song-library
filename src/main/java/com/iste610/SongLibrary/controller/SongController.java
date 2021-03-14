package com.iste610.SongLibrary.controller;

import com.iste610.SongLibrary.model.Comment;
import com.iste610.SongLibrary.model.Search;
import com.iste610.SongLibrary.model.Song;
import com.iste610.SongLibrary.repository.SongRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SongController
{
    private final SongRepository repository;

    public SongController(SongRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/songs")
    public String getAllSongs(Model model)
    {
        model.addAttribute("songs", repository.findAll());
        System.out.println("Song 1 "+ repository.findAll().get(0).getSongName());
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
        model.addAttribute("songByTitle", repository.findSongsBySongNameContains(title));
        return "index";
    }

    /**
     * Search
     */
    @RequestMapping(value = "songs/search", method = RequestMethod.GET)
    public String searchedSongs(Model model) {
        model.addAttribute("searchForm", new Search());
        return "index";
    }
    /**
     * Controller shows searched articles
     */
    @RequestMapping(value = "songs/search", method = RequestMethod.POST)
    public String searchedSongs(Search searchForm, Model model) {
        model.addAttribute("songs", repository.findSongByLyricsContains(searchForm.getLyrics()));
        return "index";
    }

    @GetMapping("/songs/{artist}")
    public String getSongByArtist(Model model, @PathVariable String artist)
    {
        model.addAttribute("songsByArtist", repository.findSongsByArtistNameContains(artist));
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

