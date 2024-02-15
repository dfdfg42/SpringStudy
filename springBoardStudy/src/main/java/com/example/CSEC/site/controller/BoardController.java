package com.example.CSEC.site.controller;


import com.example.CSEC.site.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    private final PostRepository postRepository;

    public BoardController(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("posts",postRepository.findAll());
        return "board/list";
    }

}
