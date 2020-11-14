package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private NoteService noteService;
    private UserService userService;

    public HomeController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }


    @GetMapping()
    public String homeView(Authentication authentication, Model model) {

        model.addAttribute("user", userService.getUser(authentication.getName()));
        //File Service - all file details - extraction code
       //NotesService - all note details - extraction code
      //Credentials Service - all credentials details - extraction code
      // model.addAttribute("files",filesList);

        model.addAttribute("notes", this.noteService.getNotes(userService.getUser(authentication.getName())));
      // model.addAttribute("credentialsForm",new CredForm());

        return "home";
    }







}