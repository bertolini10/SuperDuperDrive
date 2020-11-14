package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/note")
public class NoteController {

    private NoteService noteService;
    @Autowired
    private UserService userService;


    public NoteController(NoteService noteService) {
        this.noteService = noteService;

    }

    @PostMapping("/add")
    public String postNote(Authentication authentication, @ModelAttribute(value = "note")  Note note, Model model) {

            User user = userService.getUser(authentication.getName());
            note.setUserid(user.getUserid());



            if (note.getNoteid() == null){
                this.noteService.addNote(note);
                model.addAttribute("notes", this.noteService.getNotes(userService.getUser(authentication.getName())));
                model.addAttribute("add", true);
                model.addAttribute("message", "Note Added successfully");

            }else {
                this.noteService.updateNote(note);
                model.addAttribute("notes", this.noteService.getNotes(userService.getUser(authentication.getName())));
                model.addAttribute("add", true);
                model.addAttribute("message", "Note Updater successfully");
            }
            return "result";


    }



    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, Note note,Model model) {
        try {
            this.noteService.deleteNote(noteId);
            model.addAttribute("delete", true);
            model.addAttribute("message", "Note Deleted successfully");
        }catch (Exception e ){
            model.addAttribute("error", true);
            model.addAttribute("message", "Something went wrong");

        }
        return "result";

    }


}
