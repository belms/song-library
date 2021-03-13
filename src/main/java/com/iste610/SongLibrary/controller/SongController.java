package com.iste610.SongLibrary.controller;

import com.iste610.SongLibrary.repository.SongRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SongController {

    private SongRepository repository;

    public SongController(SongRepository repository){
        this.repository = repository;
    }

    @GetMapping("/songs")
    public String getAllSongs(Model model) {
        model.addAttribute("songs", repository.findAll());
        return "index";
    }
}

