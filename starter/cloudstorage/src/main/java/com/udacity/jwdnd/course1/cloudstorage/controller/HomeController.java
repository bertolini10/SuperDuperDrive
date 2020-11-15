package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private NoteService noteService;
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;

    @GetMapping()
    public String homeView(Authentication authentication, Model model) {
        model.addAttribute("user"       ,this.userService.getUser(authentication.getName()));
        model.addAttribute("files"      ,this.fileService.getFiles(userService.getUser(authentication.getName())));
        model.addAttribute("notes"      ,this.noteService.getNotes(userService.getUser(authentication.getName())));
        model.addAttribute("credentials",this.credentialService.getCredentials(userService.getUser(authentication.getName())));
        return "home";
    }







}