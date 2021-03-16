package com.iste610.SongLibrary.controller;

import com.iste610.SongLibrary.model.Comment;
import com.iste610.SongLibrary.model.Search;
import com.iste610.SongLibrary.model.Song;
import com.iste610.SongLibrary.model.SongForm;
import com.iste610.SongLibrary.repository.SongRepository;
import com.mongodb.BasicDBObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
        repository.save(newSong);
        model.addAttribute("search", new Search());
        model.addAttribute("songs", repository.findSongsBySongNameContains(newSong.getSongName()));
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
            model.addAttribute("search", new Search());
            return "index";
        }
        if (!search.getLyrics().isEmpty()) {
            model.addAttribute("songs", repository.findSongByLyricsContains(search.getLyrics()));
        } else if (!search.getArtistName().isEmpty()) {
            model.addAttribute("songs", repository.findSongsByArtistNameContains(search.getArtistName()));
        } else if (!search.getSongName().isEmpty()) {
            model.addAttribute("songs", repository.findSongsBySongNameContains(search.getSongName()));
        } else model.addAttribute("songs",new ArrayList<>());
        return "index";
    }

    /**
     * Controller redirects user to new song form page
     */
    @RequestMapping(value = "/add-comment/{id}", method = RequestMethod.GET)
    public String addCommentToSong(Model model, @PathVariable String id) {
        Song song = repository.findSongById(id);
        model.addAttribute("comment", new Comment(id,song.getSongName(), song.getArtistName()));
        return "new-comment";
    }

    @RequestMapping(value = "/add-comment/{id}", method = RequestMethod.POST)
    public String addCommentToSong(@Valid Comment comment, @PathVariable String id, Model model, BindingResult result ) {
        if (result.hasErrors()) {
            model.addAttribute("comment", new Comment());
            return "new-comment";
        }
        Song song = repository.findSongById(id);
        BasicDBObject newComment = new BasicDBObject(comment.getUser(), comment.getText());
        if(song.getComments() == null) {
            List<BasicDBObject> dbList = new ArrayList<>();
            dbList.add(newComment);
            song.setComments(dbList);
        } else song.getComments().add(newComment);
        repository.save(song);

        model.addAttribute("search", new Search());
        model.addAttribute("songs", repository.findSongById(comment.getId()));
        return "index";
    }

}

