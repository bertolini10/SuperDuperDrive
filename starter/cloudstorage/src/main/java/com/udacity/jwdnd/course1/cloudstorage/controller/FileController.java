package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String postFile(Authentication authentication, @ModelAttribute(value = "file") File file, Model model) {

        User user = userService.getUser(authentication.getName());
        file.setUserid(user.getUserid());

        if (file.getFileId() == null){
            this.fileService.addFile(file);
            model.addAttribute("message", "File Added successfully");
        }else {
            this.fileService.updateFile(file);
            model.addAttribute("message", "File Updated successfully");
        }
        model.addAttribute("files", this.fileService.getFiles(user));
        model.addAttribute("add", true);
        return "result";
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, Model model) {
        try {
            this.fileService.deleteFile(fileId);
            model.addAttribute("delete", true);
            model.addAttribute("message", "File Deleted successfully");
        }catch (Exception e ){
            model.addAttribute("error", true);
            model.addAttribute("message", "Something went wrong with File");

        }
        return "result";

    }

}
